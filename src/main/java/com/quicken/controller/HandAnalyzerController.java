package com.quicken.controller;

import com.quicken.dto.Hand;
import com.quicken.service.HandAnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.quicken.constant.GlobalConstants.API_PATH_PREFIX;
import static com.quicken.constant.GlobalConstants.IS_STRAIGHT_PATH_SUFFIX;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST Controller class for Analyzing Poker Hand
 *
 * REST Resource Name: pokerHands (Uses plural form instead of singular)
 * REST API Path name uses the noun-based REST Resource naming convention.
 * For example: API Path should start with /v1/pokerHands for interacting with pokerHands
 *
 * @author Raghu Karamel
 */
@RestController // makes this a REST Controller class
@Slf4j // enables logging
@RequestMapping(path = API_PATH_PREFIX) // set API Path Prefix as /v1/pokerHands
public class HandAnalyzerController {

    /**
     * Spring Dependency Injection (DI) for HandAnalyzerService
     */
    @Autowired
    HandAnalyzerService handAnalyzerService;

    /**
     * Method that handles the HTTP POST to /v1/pokerHands/isStraight endpoint
     *
     * @param hand
     * @return ResponseEntity
     */
    @PostMapping(path = IS_STRAIGHT_PATH_SUFFIX, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isThereAStraight(@RequestBody @Valid Hand hand){

        log.debug("Input Hand {}", hand);

        // call HandAnalyzerService
        if( handAnalyzerService.checkHandForStraight(hand) ){
            hand.setStraightFound(true);
        } else {
            hand.setStraightFound(false);
        }

        log.debug("Output Hand: {}", hand);

        // return response
        return ResponseEntity.ok(hand);
    }
}
