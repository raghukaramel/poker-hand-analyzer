package com.quicken.annotation;

import com.quicken.validator.CardValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation to validate Card Value
 * Values "2 to 10, J, Q, K, A" are only allowed
 *
 * @author Raghu Karamel
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CardValueValidator.class)
public @interface ValidateCardValue {

    public  String message() default "Invalid Card Value: it should be either 2 to 10 or J or Q or K or A";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
