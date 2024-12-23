package ru.company.shareit.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.request.ItemRequest;
import ru.company.shareit.user.User;

@Getter
@Builder
@Setter
public class ItemDto {

    Long id;

    String name;

    String description;

    Boolean available;

    User owner;

    ItemRequest request;
}
