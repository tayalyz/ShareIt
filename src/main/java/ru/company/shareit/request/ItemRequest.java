package ru.company.shareit.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.company.shareit.user.User;

import java.time.LocalDateTime;

/**
 * Id — уникальный идентификатор запроса;
 * description — текст запроса, содержащий описание требуемой вещи;
 * requester — пользователь, создавший запрос;
 * created — дата и время создания запроса.
 */

@Setter
@Getter
@Entity
@Table(name = "item_requests")
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Запрос не может быть пустым")
    @Size(max = 200, message = "Описание должно быть короче 300 символов")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @Future(message = "Дата и время создания запроса не может быть в прошлом")
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
}
