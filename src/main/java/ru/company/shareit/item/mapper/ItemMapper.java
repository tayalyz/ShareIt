package ru.company.shareit.item.mapper;

import ru.company.shareit.item.model.Item;
import ru.company.shareit.item.dto.CommentDto;
import ru.company.shareit.item.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        return toItemDto(item, new ArrayList<>());

    }
    public static ItemDto toItemDto(Item item, List<CommentDto> commentDtos) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .owner(item.getOwner())
                .comments(commentDtos)
                .request(item.getRequest() == null ? null : item.getRequest())
                .build();
    }

    public static Item fromItemDto(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .owner(itemDto.getOwner())
                .request(itemDto.getRequest() == null ? null : itemDto.getRequest())
                .build();
    }

    public static List<ItemDto> toItemDtosList(List<Item> items) {
        return items.stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
