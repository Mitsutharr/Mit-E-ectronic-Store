package com.cwm.electronic.store.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {
    //error message
    String message() default "Invalid Image Name !!";
    //re[resemt group of constrains
    Class<?>[] groups() default {};

    //additional info about annotation
    Class<? extends Payload>[] payload() default {};
}
