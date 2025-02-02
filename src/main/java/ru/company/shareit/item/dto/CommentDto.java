package ru.company.shareit.item.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * id — уникальный идентификатор комментария;
 * text — содержимое комментария;
 * item — вещь, к которой относится комментарий;
 * author — автор комментария;
 * created — дата создания комментария.
 */

@Setter
@Getter
@Builder
public class CommentDto {

    private Long id;

    @Size(max = 500, message = "Комментарий должен быть короче 500 символов")
    private String text;

    private String authorName;

    private LocalDateTime created;
}
