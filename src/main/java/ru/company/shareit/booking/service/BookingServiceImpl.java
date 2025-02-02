package ru.company.shareit.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.booking.mapper.BookingMapper;
import ru.company.shareit.booking.dto.BookingDto;
import ru.company.shareit.booking.dto.CreateBookingDto;
import ru.company.shareit.booking.model.Booking;
import ru.company.shareit.booking.model.Status;
import ru.company.shareit.booking.repository.BookingRepository;
import ru.company.shareit.exception.ForbiddenException;
import ru.company.shareit.exception.ItemIsNotAvailableException;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.item.model.Item;
import ru.company.shareit.item.repository.ItemJpaRepository;
import ru.company.shareit.user.User;
import ru.company.shareit.user.repository.UserJpaRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final UserJpaRepository userJpaRepository;

    private final ItemJpaRepository itemJpaRepository;

    @Override
    @Transactional
    public BookingDto createBooking(CreateBookingDto createBookingDto, Long bookerId) {
        User booker = getUserById(bookerId);

        Item item = itemJpaRepository.findById(createBookingDto.getItemId())
                .orElseThrow(() -> new NotFoundException("предмет c id " + createBookingDto.getItemId() + " не найден"));

        if (!item.getAvailable()) {
            log.info("предмет с id {} занят", item.getId());
            throw new ItemIsNotAvailableException("предмет занят");
        }

        Booking booking = BookingMapper.fromCreateBookingDto(createBookingDto);
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStatus(Status.WAITING);
        //booking.setState(State.WAITING);

        bookingRepository.save(booking);
        log.info("добавлен предмет с id {}", booking.getId());

        return BookingMapper.toBookingDto(booking);
    }

    @Override
    @Transactional
    public BookingDto approveBooking(Long bookingId, Boolean approved, Long userId) {
        Booking booking = BookingMapper.fromBookingDto(getById(bookingId));
        Item item = booking.getItem();

        if (!Objects.equals(item.getOwner().getId(), userId)) {
            log.info("нельзя подтвердить чужое бронирование");
            throw new ForbiddenException("нельзя подтвердить чужое бронирование");
        }
        if (approved && item.getAvailable()) {
            booking.setStatus(Status.APPROVED);
            //booking.setState(State.);
            bookingRepository.save(booking);
            log.info("бронирование с id {} подтверждено", bookingId);

            item.setAvailable(false);
            itemJpaRepository.save(item);
        } else {
            booking.setStatus(Status.REJECTED);
            //booking.setState(State.REJECTED);

            bookingRepository.save(booking);
            log.info("бронирование с id {} отклонено", bookingId);
            throw new ItemIsNotAvailableException("бронирование отклонено");
        }

        return BookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDto getById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> {
            log.info("бронирование c id {} не найдено", id);
            return new NotFoundException("бронирование c id" + id + " не найдено");
        });
        return BookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDto getBookingByBookerOrOwner(Long bookingId, Long userId) {
        BookingDto booking = getById(bookingId);
        Long itemOwnerId = booking.getItem().getOwner().getId();
        Long bookerId = booking.getBooker().getId();

        if (itemOwnerId.equals(userId) || bookerId.equals(userId)) {
            return booking;
        }
        throw new ForbiddenException("нельзя посмотреть чужое бронирование");
    }

    @Override
    public List<BookingDto> getBookings(Long userId) {
        User user = getUserById(userId);

        return BookingMapper.toBookingDtoList(bookingRepository.findAllByBooker(user));
    }

    @Override
    public List<BookingDto> getBookingsByState(Long userId, Status state) {
        if (state == null) {
            throw new ForbiddenException("статус не может быть пустым");
        }
        User user = getUserById(userId);
        return BookingMapper.toBookingDtoList(bookingRepository.findAllByBookerAndStatus(user, state));
    }

    private User getUserById(Long bookerId) {
        return userJpaRepository.findById(bookerId)
                .orElseThrow(() -> new NotFoundException("пользователь c id " + bookerId + " не найден"));
    }

}
