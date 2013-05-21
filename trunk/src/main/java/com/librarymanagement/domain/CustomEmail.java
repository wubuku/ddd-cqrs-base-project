package com.librarymanagement.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CustomEmailValidator.class)
public @interface CustomEmail {
    String message() default "Email address not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}