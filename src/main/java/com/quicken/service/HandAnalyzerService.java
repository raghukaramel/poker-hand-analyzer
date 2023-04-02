package com.quicken.service;

import com.quicken.dto.Hand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.quicken.constant.GlobalConstants.NUMERIC_CARD_VALUE_MAP;

/**
 * Poker Hand Analyzer Service class.
 * This contains the business logic for performing poker hand analysis.
 *
 * @author Raghu Karamel
 */
@Service // enables this as a service class
@Slf4j // enables logging
public class HandAnalyzerService {

    /**
     * This method analyze the given hand and determine presence of straight if any.
     *
     * @param hand
     * @return boolean flag indicating if the given hand has straight or not
     */
    public boolean checkHandForStraight(Hand hand){
        /*
        Get a distinct and sorted list of card values.
         */
        List<Integer> sortedDistinctCardValues =  hand.getCards().stream()
                .map( card -> NUMERIC_CARD_VALUE_MAP.get(card.getValue()) )
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        log.debug("Card numeric values - sorted & distinct: {}", sortedDistinctCardValues);

        /*
         Get total number of unique values left after de-duping.

         */
        int n = sortedDistinctCardValues.size();

        /*
        Return no straight found if total number of cards left after de-duping is less than 5.
        There are minimum 5 cards required in a straight.

        For example, a hand contains "Diamonds 2, Diamonds 3, Hearts 4, Clubs 5, Hearts 5, Spades 5, Diamonds 5"
        can only have 4 unique values such as 2, 3, 4, 5. So it is not enough for forming a straight.
         */
        if(n < 5){
            log.info("Straight not found");
            return false;
        }

        /*
         ----- SPECIAL CASE -----
         Check if there is a Lower end Straight starting with A, means a Straight of A, 2, 3, 4, 5

         ASSUMPTIONS:
            - The card values are unique and sorted.

         ALGORITHM:
            - A has the highest numeric value of 14 among all cards. Means it will be the last element in the list.
            - The first element in the list has to be 2 since it is the lowest value among all cards.
            - The fourth element in the last has to be 5 in order to form a straight with A
            - So an equation of "Last element - (first element + 4th element)" would be 7 when a straight exists with A

         EXAMPLE 1:
         A hand contains "Diamonds 2, Diamonds 3, Hearts 4, Clubs 5, Hearts 5, Spades 5, Diamonds A"
         would produce a sorted list of unique numeric values 2, 3, 4, 5, 14.
         So it is a straight because 14 - (2 + 5) = 7

         EXAMPLE 2:
         A hand contains "Diamonds 2, Diamonds 3, Hearts 4, Clubs 5, Hearts 10, Spades 5, Diamonds A"
         would produce a sorted list of unique numeric values 2, 3, 4, 5, 10, 14.
         So it is a straight again because 14 - (2 + 5) = 7

         EXAMPLE 3:
         A hand contains "Diamonds 2, Diamonds 4, Hearts 5, Clubs 6, Hearts 7, Spades 8, Diamonds A"
         would produce a sorted list of unique numeric values 2, 4, 5, 6, 7, 8, 14.
         So it is NOT a straight with A because 14 - (2 + 7) = 5.
         But there is a straight, and it will be detected by the next section.
         */
        if( sortedDistinctCardValues.get(n-1) - (sortedDistinctCardValues.get(0) + sortedDistinctCardValues.get(3)) == 7){
            log.info("Special case straight (A to 5) found");
            return true;
        }

        /*
        Now check the presence of other straights.

        ASSUMPTIONS:
            - The card values are unique and sorted.
            - Lower end straight with A would have already been detected if any.

        ALGORITHM:
            - There has to be 5 cards needed for forming a straight.
            - Numeric list position can be used to detect the presence of straight
                since it is sorted unique value list.
            - Iterate through the list starting from 0th index until
                the current index plus 4 is less than the size of sorted unique numeric card values (i+4 < n)
            - Check if "the value of element at 4th position after the current position" minus
                "the value of current element" is 4 or not.
            - if the above equation produces a result of 4 means there is a straight,
                else there is no straight.
            - Terminate and exit the loop as soon as a straight is found by returning "true"

         EXAMPLE 1:
         A hand contains "Diamonds 2, Diamonds 3, Hearts 4, Clubs 5, Hearts 6, Spades 6, Diamonds 6"
         would produce a sorted list of unique numeric values 2, 3, 4, 5, 6.
         In first iteration (when i=0), element at index i+4 is 6.
         Subtract the value of current element 2 from 6, and it is 4.
         So it is a straight.

         EXAMPLE 2:
         A hand contains "Diamonds 2, Diamonds 5, Hearts 6, Clubs 7, Hearts 8, Spades 9, Diamonds Q"
         would produce a sorted list of unique numeric values 2, 5, 6, 7, 8, 9, 12.
         In second iteration (when i=1), element at index i+4 is 9.
         Subtract the value of current element 5 from 9, and it is 4.
         So it is a straight.

         EXAMPLE 3:
         A hand contains "Diamonds 7, Diamonds 10, Hearts A, Clubs J, Hearts K, Spades 8, Diamonds Q"
         would produce a sorted list of unique numeric values 7, 8, 10, 11, 12, 13, 14.
         In third iteration (when i=2), element at index i+4 is 14.
         Subtract the value of current element 10 from 14, and it is 4.
         So it is a straight.

         EXAMPLE 4:
         A hand contains "Diamonds 7, Diamonds 10, Hearts 2, Clubs J, Hearts K, Spades 8, Diamonds Q"
         would produce a sorted list of unique numeric values 2, 7, 8, 10, 11, 12, 13.
         All iterations produce a non 4 result and the loop ends without detecting a straight.
         */
        for(int i=0; i+4 < n; i++){
            if(sortedDistinctCardValues.get(i+4) - sortedDistinctCardValues.get(i) == 4){
                log.info("Straight found");
                return true;
            }
        }

        /*
        If no straight found so far means there is no straight present in the given hand.
        So return false.
         */
        log.info("Straight not found");
        return false;
    }
}
