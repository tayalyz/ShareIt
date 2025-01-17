package ru.company.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "Имя/логин не может быть пустым")
    @Size(max = 50, message = "Имя/логин должно быть короче 50 символов")
    private String name;

    @NotNull
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Неверный формат электронной почты")
    private String email;
}
