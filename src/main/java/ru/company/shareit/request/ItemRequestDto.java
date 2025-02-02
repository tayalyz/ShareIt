package ru.company.shareit.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;

@Setter
@Getter
public class ItemRequestDto {

    private Long id;

    @NotBlank(message = "Запрос не может быть пустым")
    @Size(max = 200, message = "Описание должно быть короче 300 символов")
    private String description;

    private User requester;

    private LocalDateTime created;
}
