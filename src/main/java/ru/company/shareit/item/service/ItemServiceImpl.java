package ru.company.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.item.Item;
import ru.company.shareit.item.dto.ItemDto;
import ru.company.shareit.item.ItemMapper;
import ru.company.shareit.item.dto.ItemUpdateDto;
import ru.company.shareit.item.repository.ItemRepository;
import ru.company.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    public ItemDto getItemById(Long id) {
        Item item = itemRepository.getItemById(id).orElseThrow(() -> {
            log.info("предмет с id {} не найден", id);
            return new NotFoundException("предмет не найден");
        });
        return ItemMapper.toItemDto(item);
    }

    public ItemDto addItem(ItemDto itemDto, Long userId) {
        itemDto.setOwner(userRepository.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("пользователь c id " + userId + " не найден")));

        Item newItem = itemRepository.addItem(ItemMapper.fromItemDto(itemDto)).orElseThrow(() -> {
            log.info("предмет с названием {} не создан", itemDto.getName());
            return new RuntimeException("предмет не создан");
        });

        log.info("добавлен предмет с id {}", newItem.getId());
        return ItemMapper.toItemDto(newItem);
    }

    public ItemDto updateItem(Long userId, Long id, ItemUpdateDto itemDto) {
        ItemDto item = getItemById(id);
        userRepository.getUserById(userId).orElseThrow(() -> new NotFoundException("пользователь не найден"));

        Optional.ofNullable(itemDto.getName())
                .ifPresent(item::setName);

        Optional.ofNullable(itemDto.getDescription())
                .ifPresent(item::setDescription);

        Optional.ofNullable(itemDto.getAvailable())
                .ifPresent(item::setAvailable);

        Item updatedItem = itemRepository.updateItem(ItemMapper.fromItemDto(item)).orElseThrow(() -> {
            log.info("предмет с названием {} не обновлен", item.getName());
            return new RuntimeException("предмет не обновлен");
        });

        log.info("обновлен предмет с id {}", id);
        return ItemMapper.toItemDto(updatedItem);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteItemById(id);
        log.info("удален предмет с id {}", id);
    }

    public List<ItemDto> getAllItemsByUserId(Long userId) {
        return itemRepository.getAllItems().values().stream()
                .filter(item -> Objects.equals(item.getOwner().getId(), userId))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsWithName(String text) {
        return text.isBlank() ? new ArrayList<>() : itemRepository.getAllItems().values().stream()
                .filter(item ->
                        item.getName().toLowerCase().contains(text.toLowerCase()) || item.getDescription().toLowerCase().contains(text.toLowerCase()))
                .filter(Item::getAvailable)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
