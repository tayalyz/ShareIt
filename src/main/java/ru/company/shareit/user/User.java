package ru.company.shareit.user;

import lombok.*;

/**
 * id — уникальный идентификатор пользователя;
 * name — имя или логин пользователя;
 * email — адрес электронной почты (учтите, что два пользователя не могут иметь одинаковый адрес электронной почты).
 */

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class User {

    private Long id;

    private String name;

    private String email;
}
