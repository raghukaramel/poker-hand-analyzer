package com.quicken.annotation;

import com.quicken.validator.CardSuitValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation to validate Card Suit
 * Suits D, H, C, S are only allowed. These values described as follows
 *      D: Diamonds
 *      H: Hearts
 *      C: Clubs
 *      S: Spades
 *
 * @author Raghu Karamel
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CardSuitValidator.class)
public @interface ValidateCardSuit {

    public  String message() default "Invalid Card Suit: it should be either D or H or C or S";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
