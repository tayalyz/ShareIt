package ru.company.shareit.booking.mapper;

import lombok.RequiredArgsConstructor;
import ru.company.shareit.booking.dto.BookingDto;
import ru.company.shareit.booking.dto.CreateBookingDto;
import ru.company.shareit.booking.model.Booking;
import ru.company.shareit.item.model.Item;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookingMapper {

    public static BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .booker(booking.getBooker())
                .item(booking.getItem())
                .status(booking.getStatus())
                .build();
    }

    public static Booking fromBookingDto(BookingDto bookingDto) { // todo тут можно передавать айтем?
        return Booking.builder()
                .id(bookingDto.getId())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .booker(bookingDto.getBooker())
                .item(bookingDto.getItem())
                .status(bookingDto.getStatus())
                .build();
    }

    public static Booking fromCreateBookingDto(CreateBookingDto createBookingDto, Item item) { // todo тут можно передавать айтем?
        return Booking.builder()
                .id(createBookingDto.getId())
                .start(createBookingDto.getStart())
                .end(createBookingDto.getEnd())
                .booker(createBookingDto.getBooker())
                .item(item)
                .status(createBookingDto.getStatus())
                .build();
    }
    public static Booking fromCreateBookingDto(CreateBookingDto createBookingDto) { // todo тут можно передавать айтем?
        return Booking.builder()
                .id(createBookingDto.getId())
                .start(createBookingDto.getStart())
                .end(createBookingDto.getEnd())
                .booker(createBookingDto.getBooker())
                .status(createBookingDto.getStatus())
                .build();
    }

    public static List<BookingDto> toBookingDtoList(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingMapper::toBookingDto)
                .collect(Collectors.toList());
    }
}
