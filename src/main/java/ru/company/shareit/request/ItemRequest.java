package ru.company.shareit.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;

/**
 * id — уникальный идентификатор запроса;
 * description — текст запроса, содержащий описание требуемой вещи;
 * requester — пользователь, создавший запрос;
 * created — дата и время создания запроса.
 */

@Setter
@Getter
public class ItemRequest {

    private Long id;

    @NotBlank(message = "Запрос не может быть пустым")
    @Size(max = 200, message = "Описание должно быть короче 300 символов")
    private String description;

    private User requester;

    @Future(message = "Дата и время создания запроса не может быть в прошлом")
    private LocalDateTime created;
}
