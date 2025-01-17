package ru.company.shareit.item;

/**
 * name — краткое название;
 * description — развёрнутое описание;
 * available — статус о том, доступна или нет вещь для аренды;
 * owner — владелец вещи;
 * request — если вещь была создана по запросу другого пользователя, то в этом поле будет храниться ссылка на соответствующий запрос.
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.request.ItemRequest;
import ru.company.shareit.user.User;

@Getter
@Setter
@Builder
public class Item {

    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private User owner;

    private ItemRequest request;
}
