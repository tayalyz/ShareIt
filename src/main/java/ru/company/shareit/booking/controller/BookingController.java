package ru.company.shareit.booking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.company.shareit.booking.dto.BookingDto;
import ru.company.shareit.booking.service.BookingService;
import ru.company.shareit.booking.dto.CreateBookingDto;
import ru.company.shareit.booking.model.Status;

import java.util.List;

/**
 * Добавление нового запроса на бронирование.
 * Запрос может быть создан любым пользователем, а затем подтверждён владельцем вещи.
 * Эндпоинт — POST /bookings.
 * После создания запрос находится в статусе WAITING — «ожидает подтверждения».
 *
 * Подтверждение или отклонение запроса на бронирование.
 * Может быть выполнено только владельцем вещи. Затем статус бронирования становится либо APPROVED, либо REJECTED.
 * Эндпоинт — PATCH /bookings/{bookingId}?approved={approved}, параметр approved может принимать значения true или false.
 *
 * Получение данных о конкретном бронировании (включая его статус).
 * Может быть выполнено либо автором бронирования, либо владельцем вещи, к которой относится бронирование.
 * Эндпоинт — GET /bookings/{bookingId}.
 *
 * Получение списка всех бронирований текущего пользователя.
 * Эндпоинт — GET /bookings?state={state}.
 * Параметр state необязательный и по умолчанию равен ALL (англ. «все»). Также он может принимать значения CURRENT (англ. «текущие»), PAST (англ. «завершённые»), FUTURE (англ. «будущие»), WAITING (англ. «ожидающие подтверждения»), REJECTED (англ. «отклонённые»).
 * Бронирования должны возвращаться отсортированными по дате от более новых к более старым.
 *
 * Получение списка бронирований для всех вещей текущего пользователя.
 * Эндпоинт — GET /bookings/owner?state={state}. Этот запрос имеет смысл для владельца хотя бы одной вещи. Работа параметра state аналогична его работе в предыдущем сценарии.
 */

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto createBooking(@RequestBody @Valid CreateBookingDto bookingDto, @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.createBooking(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@PathVariable Long bookingId, @RequestParam Boolean approved,  @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.approveBooking(bookingId, approved, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingByBookerOrOwner(@PathVariable Long bookingId, @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.getBookingByBookerOrOwner(bookingId, userId);
    }

    @GetMapping
    public List<BookingDto> getBookings(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.getBookings(userId);
    }

    @GetMapping("/owner")
    public List<BookingDto> getBookingsByState(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestParam(required = false) Status state) {
        return bookingService.getBookingsByState(userId, state);  // todo что за state пока не понятно
    }
}
