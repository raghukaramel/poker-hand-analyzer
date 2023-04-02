package com.quicken.validator;

import com.quicken.annotation.ValidateCardValue;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.quicken.constant.GlobalConstants.NUMERIC_CARD_VALUE_MAP;

/**
 * A custom constraint validator class to validate Card Value
 *
 * @author Raghu Karamel
 */
@Slf4j //enables logging
public class CardValueValidator implements ConstraintValidator<ValidateCardValue, String> {

    /**
     * Provides custom logic for validating Card Value.
     * It checks the given Card Valid is valid by looking the NUMERIC_CARD_VALUE_MAP defined in Global Constant class.
     *
     * @param value
     * @param constraintValidatorContext
     * @return a boolean flag indicating the validation was successful or not
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(NUMERIC_CARD_VALUE_MAP.containsKey(value)){
            log.debug("Card value validation successful");
            return true;
        } else {
            log.error("Card value validation failed");
            return false;
        }
    }
}
