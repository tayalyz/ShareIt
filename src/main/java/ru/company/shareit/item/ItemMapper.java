package ru.company.shareit.item;

public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.id,
                item.name,
                item.description,
                item.available,
                item.owner,
                item.request == null ? null : item.getRequest()
        );
    }

    public static Item fromItemDto(ItemDto item) {
        return new Item(
                item.id,
                item.name,
                item.description,
                item.available,
                item.owner,
                item.request == null ? null : item.getRequest()
        );
    }
}
