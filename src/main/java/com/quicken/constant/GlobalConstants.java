package com.quicken.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Constants used across the application
 *
 * @author Raghu Karamel
 */
public final class GlobalConstants {

    /**
     * Private constructor makes it true static class
     * by not allowing to create an instance outside
     */
    private GlobalConstants(){};

    /**
     * API Path prefix used across the application
     */
    public static final String API_PATH_PREFIX = "/v1/pokerHands";

    /**
     * API Path Suffix used for isStraight API
     */
    public static final String IS_STRAIGHT_PATH_SUFFIX = "/isStraight";

    /**
     * Valid card suits
     */
    public static final HashSet<String> CARD_SUITS = new HashSet<>(Arrays.asList("D", "H", "C", "S"));

    /**
     * Valid card values
     */
    public static final Map<String, Integer> NUMERIC_CARD_VALUE_MAP = Map.ofEntries(
            entry("2", 2),
            entry("3", 3),
            entry("4", 4),
            entry("5", 5),
            entry("6", 6),
            entry("7", 7),
            entry("8", 8),
            entry("9", 9),
            entry("10", 10),
            entry("J", 11),
            entry("Q", 12),
            entry("K", 13),
            entry("A", 14)
    );

    /**
     * Poker hand size. Hand with other size is invalid
     */
    public static final int HAND_SIZE = 7;
}
