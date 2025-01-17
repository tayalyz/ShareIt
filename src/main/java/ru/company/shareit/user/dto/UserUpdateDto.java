package ru.company.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserUpdateDto {

    @Size(max = 50, message = "Имя/логин должно быть короче 50 символов")
    private String name;

    @Email(message = "Неверный формат электронной почты")
    private String email;
}
