package ru.company.shareit.booking.model;

// `ALL` (англ. «все»). Также он может принимать значения `CURRENT` (англ. «текущие»),
// `PAST` (англ. «завершённые»), `FUTURE` (англ. «будущие»), `WAITING` (англ. «ожидающие подтверждения»), `REJECTED` (англ. «отклонённые»).
//   Бронирования должны возвращаться отсортированными по дате от более новых к более старым.

public enum State {
    ALL,
    FUTURE,
    WAITING,
    PAST,
    REJECTED
}
