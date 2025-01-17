package ru.company.shareit.item.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.request.ItemRequest;
import ru.company.shareit.user.User;

@Getter
@Builder
@Setter
public class ItemUpdateDto {

    @Size(max = 50, message = "Название должно быть короче 50 символов")
    private String name;

    @Size(max = 200, message = "Описание должно быть короче 300 символов")
    private String description;

    private Boolean available;

    private User owner;

    ItemRequest request;
}
