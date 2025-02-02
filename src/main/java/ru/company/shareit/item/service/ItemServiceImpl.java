package ru.company.shareit.item.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.booking.model.Booking;
import ru.company.shareit.booking.model.Status;
import ru.company.shareit.booking.repository.BookingRepository;
import ru.company.shareit.exception.CommentIsNotAllowedException;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.item.dto.CommentDto;
import ru.company.shareit.item.dto.ItemDto;
import ru.company.shareit.item.dto.ItemUpdateDto;
import ru.company.shareit.item.mapper.CommentMapper;
import ru.company.shareit.item.mapper.ItemMapper;
import ru.company.shareit.item.model.Comment;
import ru.company.shareit.item.model.Item;
import ru.company.shareit.item.repository.CommentRepository;
import ru.company.shareit.item.repository.ItemJpaRepository;
import ru.company.shareit.user.User;
import ru.company.shareit.user.repository.UserJpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final UserJpaRepository userRepository;

    private final ItemJpaRepository itemRepository;

    private final CommentRepository commentRepository;

    private final BookingRepository bookingRepository;

    @Override
    public ItemDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> {
            log.info("предмет с id {} не найден", id);
            return new NotFoundException("предмет не найден");
        });

        List<Comment> existComments = commentRepository.findAllCommentsByItem_Id(id);
        List<CommentDto> commentDtos = CommentMapper.toCommentDtoList(existComments);
        return ItemMapper.toItemDto(item, commentDtos);
    }

    @Override
    @Transactional
    public ItemDto addItem(ItemDto itemDto, Long userId) {
        User owner = getUserById(userId);

        Item item = ItemMapper.fromItemDto(itemDto);
        item.setOwner(owner);

        itemRepository.save(item);
        log.info("добавлен предмет с id {}", item.getId());

        return ItemMapper.toItemDto(item);
    }

    @Override
    @Transactional
    public ItemDto updateItem(Long userId, Long id, ItemUpdateDto itemDto) {
        Item item = ItemMapper.fromItemDto(getItemById(id));

        getUserById(userId);

        Optional.ofNullable(itemDto.getName())
                .ifPresent(item::setName);

        Optional.ofNullable(itemDto.getDescription())
                .ifPresent(item::setDescription);

        Optional.ofNullable(itemDto.getAvailable())
                .ifPresent(item::setAvailable);

        itemRepository.save(item);

        log.info("обновлен предмет с id {}", id);
        return ItemMapper.toItemDto(item);
    }

    @Override
    @Transactional
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
        log.info("удален предмет с id {}", id);
    }

    @Override
    public List<ItemDto> getAllItemsByUserId(Long userId) {
        List<Item> items = itemRepository.findAllByOwner_IdFetch(userId);
        return items.stream()
                .map(item -> ItemMapper.toItemDto(item, CommentMapper.toCommentDtoList(commentRepository.findAllCommentsByItem_Id(item.getId()))))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsWithName(String text) {
        return text.isBlank() ? new ArrayList<>() :
                itemRepository.findAllByNameContainingIgnoreCaseAndAvailableOrDescriptionContainingIgnoreCaseAndAvailable(text, true, text, true).stream()
                        .map(ItemMapper::toItemDto)
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto, Long itemId, Long userId) {
        Item item = ItemMapper.fromItemDto(getItemById(itemId));
        User author = getUserById(userId);

        Booking booking = bookingRepository.findByBooker_IdAndItem_IdAndStatusLikeAndEndIsBeforeOrderByEndAsc(author.getId(),
                itemId, Status.APPROVED, LocalDateTime.now());

        if (booking == null) {
            log.info("нельзя оставить комментарий, если вы не брали эту вещь в аренду");
            throw new CommentIsNotAllowedException("нельзя оставить комментарий, если вы не брали эту вещь в аренду");
        }

        Comment comment = CommentMapper.fromCommentDto(commentDto, item, author);
        comment.setCreated(LocalDateTime.now());
        commentRepository.save(comment);
        log.info("комментарий с id {} добавлен к вещи с id {}", userId, itemId);

        return CommentMapper.toCommentDto(comment);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.info("пользователь с id {} не найден", userId);
            return new NotFoundException("пользователь c id " + userId + " не найден");
        });
    }
}
