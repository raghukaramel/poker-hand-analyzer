package com.quicken.util;

import com.quicken.dto.Card;
import com.quicken.dto.Hand;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A utility class with static functions to perform some generic tasks
 *
 * @author Raghu Karamel
 */
public class PokerUtil {

    /**
     * Private constructor to avoid creating an object of this class
     */
    private PokerUtil(){}

    /**
     * Construct a Hand object from a comma separated cards
     *
     * @param s
     * @return a hand object constructed from the given string
     */
    public static Hand convertCardStringToHand(String s){
        return Hand.builder().cards(
                    Arrays.stream(s.split("\\s*,\\s*"))
                            .map(c -> Card.builder().suit(c.substring(0,1)).value(c.substring(1)).build())
                            .collect(Collectors.toList())
                ).build();
    }
}
