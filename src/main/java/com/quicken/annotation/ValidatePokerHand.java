package com.quicken.annotation;

import com.quicken.validator.PokerHandValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation to validate Poker Hand
 * It must have 7 distinct cards
 *
 * @author Raghu Karamel
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PokerHandValidator.class)
public @interface ValidatePokerHand {

    public  String message() default "Invalid Poker Hand: there should be exactly 7 distinct cards present in a hand.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
