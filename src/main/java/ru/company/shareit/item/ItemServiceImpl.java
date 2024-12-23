package ru.company.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.user.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        itemDto.owner = userRepository.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("пользователь c id " + userId + " не найден"));

        Item item = ItemMapper.fromItemDto(itemDto);
        itemRepository.addItem(item);
        log.info("добавлен предмет с id {}", item.id);
        return getItemById(item.id);
    }

    public ItemDto updateItem(Long userId, Long id, Map<String, Object> fields) {
        ItemDto item = getItemById(id);
        userRepository.getUserById(userId).orElseThrow(() -> new NotFoundException("пользователь не найден"));
        if (fields.containsKey("name")) {
            item.setName((String) fields.get("name"));
        }
        if (fields.containsKey("description")) {
            item.setDescription((String) fields.get("description"));
        }
        if (fields.containsKey("available")) {
            item.setAvailable((Boolean) fields.get("available"));
        }

        itemRepository.updateItem(ItemMapper.fromItemDto(item));
        log.info("обновлен предмет с id {}", id);
        return item;
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteItemById(id);
        log.info("удален предмет с id {}", id);
    }

    public List<ItemDto> getAllItemsByUserId(Long userId) {
        return itemRepository.getAllItems().values().stream()
                .filter(item -> Objects.equals(item.owner.getId(), userId))
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsWithName(String text) {
        return itemRepository.getAllItems().values().stream()
                .filter(item -> item.name.toLowerCase().contains(text.toLowerCase()) || item.description.toLowerCase().contains(text.toLowerCase()))
                .filter(item -> item.available)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
