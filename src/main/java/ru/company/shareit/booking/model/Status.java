package ru.company.shareit.booking.model;

/**
 * Описание.
 *
 * status — статус бронирования. Может принимать одно из следующих значений:
 * WAITING — новое бронирование, ожидает одобрения.
 * APPROVED — бронирование подтверждено владельцем.
 * REJECTED — бронирование отклонено владельцем.
 * CANCELED — бронирование отменено создателем.
 */

public enum Status {
    WAITING,
    APPROVED,
    REJECTED,
    CANCELED
}
