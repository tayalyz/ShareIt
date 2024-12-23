package ru.company.shareit.item;

import java.util.List;
import java.util.Map;

public interface ItemService {

    ItemDto getItemById(Long id);

    ItemDto addItem(ItemDto itemDto, Long userId);

    ItemDto updateItem(Long userId, Long id, Map<String, Object> fields);

    void deleteItemById(Long id);

    List<ItemDto> getAllItemsByUserId(Long userId);

    List<ItemDto> getItemsWithName(String text);
}
