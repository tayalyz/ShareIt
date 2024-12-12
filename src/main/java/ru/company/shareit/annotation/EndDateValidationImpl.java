package ru.company.shareit.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class EndDateValidationImpl implements ConstraintValidator<EndDateValidation, LocalDateTime> {

    private LocalDateTime startDate;

    @Override
    public boolean isValid(LocalDateTime end, ConstraintValidatorContext context) {
        if (startDate == null || end == null) return true;

        return end.isAfter(startDate);
    }
}
