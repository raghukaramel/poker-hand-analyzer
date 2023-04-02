package com.quicken.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quicken.dto.Hand;
import com.quicken.service.HandAnalyzerService;
import com.quicken.util.PokerUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.quicken.constant.GlobalConstants.API_PATH_PREFIX;
import static com.quicken.constant.GlobalConstants.IS_STRAIGHT_PATH_SUFFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HandAnalyzerController.class)
public class HandAnalyzerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HandAnalyzerService handAnalyzerService;

    @Test
    public void shouldReturnStraightFound() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("D2, H4, C6, S8, D10, HQ, CA");
        when(handAnalyzerService.checkHandForStraight(hand)).thenReturn(true);
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isOk())
                .andDo(res -> {
                    Hand output = objectMapper.readValue(res.getResponse().getContentAsString(), Hand.class);
                    assertThat(output.isStraightFound()).isTrue();
                });
    }

    @Test
    public void shouldReturnStraightNotFound() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("D2, H4, C6, S8, D10, HQ, CA");
        when(handAnalyzerService.checkHandForStraight(hand)).thenReturn(false);
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isOk())
                .andDo(res -> {
                    Hand output = objectMapper.readValue(res.getResponse().getContentAsString(), Hand.class);
                    assertThat(output.isStraightFound()).isFalse();
                });
    }

    @Test
    public void shouldReturnCardSuitValidationError() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("A2, H4, C6, S8, D10, HQ, CA");
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isBadRequest())
                .andDo(res ->
                    assertThat(res.getResponse().getContentAsString())
                            .contains("Invalid Card Suit: it should be either D or H or C or S"));
    }

    @Test
    public void shouldReturnCardValueValidationError() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("D2, H4, C6, S8000, D10, HQ, CA");
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isBadRequest())
                .andDo(res ->
                        assertThat(res.getResponse().getContentAsString())
                                .contains("Invalid Card Value: it should be either 2 to 10 or J or Q or K or A"));
    }

    @Test
    public void shouldReturnCardsLengthValidationError() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("D2, H4, C6, S8, D10, HQ");
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isBadRequest())
                .andDo(res ->
                        assertThat(res.getResponse().getContentAsString())
                                .contains("Invalid Poker Hand: there should be exactly 7 distinct cards present in a hand."));
    }

    @Test
    public void shouldReturnCardsLengthValidationErrorWhenDuplicatePresent() throws Exception {
        Hand hand = PokerUtil.convertCardStringToHand("D2, H4, C6, S8, D10, HQ, HQ");
        this.mockMvc.perform(post(API_PATH_PREFIX+IS_STRAIGHT_PATH_SUFFIX)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hand)))
                .andExpect(status().isBadRequest())
                .andDo(res ->
                        assertThat(res.getResponse().getContentAsString())
                                .contains("Invalid Poker Hand: there should be exactly 7 distinct cards present in a hand."));
    }
}
