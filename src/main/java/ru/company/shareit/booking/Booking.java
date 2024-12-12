package ru.company.shareit.booking;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.annotation.EndDateValidation;
import ru.company.shareit.item.Item;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;

/**
 * id — уникальный идентификатор бронирования;
 * start — дата и время начала бронирования;
 * end — дата и время конца бронирования;
 * item — вещь, которую пользователь бронирует;
 * booker — пользователь, осуществляющий бронирование;
 * status — статус бронирования. Может принимать одно из следующих значений:
 * WAITING — новое бронирование, ожидает одобрения.
 * APPROVED — бронирование подтверждено владельцем.
 * REJECTED — бронирование отклонено владельцем.
 * CANCELED — бронирование отменено создателем.
 */

@Setter
@Getter
public class Booking {

    Long id;

    @Future(message = "Дата начала бронирования не может быть в прошлом")
    LocalDateTime start;

    @EndDateValidation
    @Future(message = "Дата конца бронирования не может быть в прошлом")
    LocalDateTime end;

    Item item;

    User booker;

    Status status;
}
