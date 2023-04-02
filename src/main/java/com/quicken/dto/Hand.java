package com.quicken.dto;

import com.quicken.annotation.ValidatePokerHand;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * A Data Transfer Object (DTO) class for Poker Hand
 *
 * This DTO class is used for both Input and Output
 *
 * @author Raghu Karamel
 */
@Data // a lombok shortcut annotation for auto-generating Getters, Setters, toString() etc.
@Builder // a lombok annotation produces complex builder APIs
public class Hand implements Serializable {

    /**
     * A boolean flag indicates if the given hand is a Straight or not
     *
     * No need to set this when using this class in the Input.
     * It will be ignored anyway even if you set it in the Input.
     * You can't bluff it by setting the flag in input :)
     */
    boolean straightFound;

    /**
     * List of cards in a hand.
     *
     * The custom validator attached to it will validate the following -
     *      - duplicate cards
     *      - hand size
     */
    @ValidatePokerHand // custom annotation to validate hand
    private List<@Valid Card> cards;

}
