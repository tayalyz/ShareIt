package ru.company.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * id — уникальный идентификатор пользователя;
 * name — имя или логин пользователя;
 * email — адрес электронной почты (учтите, что два пользователя не могут иметь одинаковый адрес электронной почты).
 */

@Setter
@Getter
public class User {

    Long id;

    @NotBlank(message = "Имя/логин не может быть пустым")
    @Size(max = 50, message = "Имя/логин должно быть короче 50 символов")
    String name;

    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Неверный формат электронной почты")
    String email;
}
