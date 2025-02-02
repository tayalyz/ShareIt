package ru.company.shareit.item.service;

import ru.company.shareit.item.dto.CommentDto;
import ru.company.shareit.item.dto.ItemDto;
import ru.company.shareit.item.dto.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemDto getItemById(Long id);

    ItemDto addItem(ItemDto itemDto, Long userId);

    ItemDto updateItem(Long userId, Long id, ItemUpdateDto itemUpdateDto);

    void deleteItemById(Long id);

    List<ItemDto> getAllItemsByUserId(Long userId);

    List<ItemDto> getItemsWithName(String text);

    CommentDto addComment(CommentDto commentDto, Long itemId, Long userId);
}
