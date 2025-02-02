package ru.company.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.request.ItemRequest;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Setter
public class ItemDto {

    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 50, message = "Название должно быть короче 50 символов")
    private String name;

    @NotBlank
    @Size(max = 300, message = "Описание должно быть короче 300 символов")
    private String description;

    @NotNull
    private Boolean available;

    private User owner;

    private ItemRequest request;

    private List<CommentDto> comments;

    private LocalDateTime lastBooking;

    private LocalDateTime nextBooking;
}
