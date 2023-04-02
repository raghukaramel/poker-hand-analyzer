package com.quicken.validator;

import com.quicken.dto.Card;
import com.quicken.annotation.ValidatePokerHand;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.quicken.constant.GlobalConstants.HAND_SIZE;

/**
 * A custom constraint validator class to validate Poker Hand
 *
 * @author Raghu Karamel
 */
@Slf4j // enable logging
public class PokerHandValidator implements ConstraintValidator<ValidatePokerHand, List<Card>> {

    /**
     * Provides custom logic for validating Poker Hand.
     * It checks the following
     *  - The total number of cards provided in the Input.
     *  - Presence of duplicate cards
     *
     * @param list
     * @param constraintValidatorContext
     * @return a boolean flag indicating the validation was successful or not
     */
    @Override
    public boolean isValid(List<Card> list, ConstraintValidatorContext constraintValidatorContext) {

        /*
        A valid poker hand should have exactly 7 cards.
        So check the total number of cards and return false if there are
        less than or more than 7 cards present in the input
         */
        if(list.size() != HAND_SIZE ) {
            log.error("Invalid hand. There are {} cards in the hand instead of 7", list.size());
            return false;
        }

        /*
        There could be duplcate cards provided in the input.
        So check for the presence of duplicate cards and
        return false if duplicate cards found.
         */
        Set<String> distinctCards = list.stream()
                .map(c -> c.getSuit()+c.getValue())
                .collect(Collectors.toSet());
        if(distinctCards.size() != HAND_SIZE){
            log.error("Invalid hand. There are duplicate cards in the hand", list.size());
            return false;
        }

        /*
        Return hand is valid if it gets here.
         */
        log.debug("Hand validation successful");
        return true;
    }
}
