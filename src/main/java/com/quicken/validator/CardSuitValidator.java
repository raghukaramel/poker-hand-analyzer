package com.quicken.validator;

import com.quicken.annotation.ValidateCardSuit;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.quicken.constant.GlobalConstants.CARD_SUITS;

/**
 * A custom constraint validator class to validate Card Suit
 *
 * @author Raghu Karamel
 */
@Slf4j // enables logging
public class CardSuitValidator implements ConstraintValidator<ValidateCardSuit, String> {

    /**
     * Provides custom logic for validating Card suit.
     * It checks the given Card Suit is valid by looking the CARD_SUITS defined in Global Constant class.
     *
     * @param suit
     * @param constraintValidatorContext
     * @return a boolean flag indicating the validation was successful or not
     */
    @Override
    public boolean isValid(String suit, ConstraintValidatorContext constraintValidatorContext) {
        if(CARD_SUITS.contains(suit)){
            log.debug("Card suit validation successful");
            return true;
        } else {
            log.error("Card suit validation failed");
            return false;
        }
    }
}
