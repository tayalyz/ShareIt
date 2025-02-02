package ru.company.shareit.booking.service;

import ru.company.shareit.booking.model.Status;
import ru.company.shareit.booking.dto.BookingDto;
import ru.company.shareit.booking.dto.CreateBookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(CreateBookingDto bookingDto, Long userId);

    BookingDto approveBooking(Long bookingId, Boolean approved, Long userId);

    BookingDto getById(Long id);

    BookingDto getBookingByBookerOrOwner(Long bookingId, Long userId);

    List<BookingDto> getBookings(Long userId);

    List<BookingDto> getBookingsByState(Long userId, Status state);
}
