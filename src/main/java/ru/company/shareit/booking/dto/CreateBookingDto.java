package ru.company.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.company.shareit.annotation.EndDateValidation;
import ru.company.shareit.booking.model.State;
import ru.company.shareit.booking.model.Status;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CreateBookingDto {

    private Long id;

    @NotNull
    @Future(message = "Дата начала бронирования не может быть в прошлом")
    private LocalDateTime start;

    @NotNull
    @EndDateValidation
    @Future(message = "Дата конца бронирования не может быть в прошлом или равна дате начала")
    private LocalDateTime end;

    @NotNull
    private Long itemId;

    private User booker;

    private Status status;

    private State state; // todo что это?
}
