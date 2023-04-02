package com.quicken.dto;

import com.quicken.annotation.ValidateCardSuit;
import com.quicken.annotation.ValidateCardValue;
import lombok.Builder;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) class for Card
 *
 * @author Raghu Karamel
 */
@Data // a lombok shortcut annotation for auto-generating Getters, Setters, toString() etc.
@Builder // a lombok annotation produces complex builder APIs
public class Card {

    /**
     * Card Suit
     */
    @ValidateCardSuit // custom annotation to validate suit
    private String suit;

    /**
     * Card Value
     */
    @ValidateCardValue // custom annotation to validate value
    private String value;

}
