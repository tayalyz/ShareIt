package ru.company.shareit.item;

/**
 * name — краткое название;
 * description — развёрнутое описание;
 * available — статус о том, доступна или нет вещь для аренды;
 * owner — владелец вещи;
 * request — если вещь была создана по запросу другого пользователя, то в этом поле будет храниться ссылка на соответствующий запрос.
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.request.ItemRequest;
import ru.company.shareit.user.User;

@Getter
@Setter
@Builder
public class Item {

    Long id;

    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 50, message = "Название должно быть короче 50 символов")
    String name;

    @NotBlank
    @Size(max = 200, message = "Описание должно быть короче 300 символов")
    String description;

    @NotNull
    Boolean available;

    User owner;

    ItemRequest request;
}
