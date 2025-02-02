package ru.company.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.shareit.booking.model.Booking;
import ru.company.shareit.booking.model.Status;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBooker_IdAndEndIsBefore(Long bookerId, LocalDateTime end);

    List<Booking> findAllByBooker(User booker);

    List<Booking> findAllByBookerAndStatus(User booker, Status status);

    Booking findByBooker_IdAndItem_IdAndStatusLikeAndEndIsBeforeOrderByEndAsc(Long bookerId, Long itemId, Status status, LocalDateTime end);
}
