package ru.company.shareit.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = EndDateValidationImpl.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndDateValidation {

    String message() default "Дата окончания должна быть после даты начала";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
