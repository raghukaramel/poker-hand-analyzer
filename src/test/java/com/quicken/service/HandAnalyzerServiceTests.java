package com.quicken.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.quicken.dto.Hand;
import com.quicken.util.PokerUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HandAnalyzerServiceTests {

    @Autowired
    private HandAnalyzerService handAnalyzerService;

    @Test
    public void shouldReturnLowerStraight(){
        String testData = "D2, H3, C4, S5, D6, H8, C10";
        assertThat(checkHandForStraight(testData)).isTrue();
    }

    @Test
    public void shouldReturnMidStraight(){
        String testData = "D3, H6, C7, S8, D9, H10, CA";
        assertThat(checkHandForStraight(testData)).isTrue();
    }

    @Test
    public void shouldReturnHighStraight(){
        String testData = "D3, H4, C8, S9, D10, HJ, CQ";
        assertThat(checkHandForStraight(testData)).isTrue();
    }

    @Test
    public void shouldReturnLowerStraightWithA(){
        String testData = "D2, H3, C4, S5, D7, H8, CA";
        assertThat(checkHandForStraight(testData)).isTrue();
    }

    @Test
    public void shouldReturnNoStraight(){
        String testData = "D2, H4, C6, S8, D10, HQ, CA";
        assertThat(checkHandForStraight(testData)).isFalse();
    }

    @Test
    public void shouldReturnNoStraightWithDuplicates(){
        String testData = "D2, H2, C6, S6, D6, H6, CA";
        assertThat(checkHandForStraight(testData)).isFalse();
    }
    private boolean checkHandForStraight(String testData){
        Hand hand = PokerUtil.convertCardStringToHand(testData);
        return handAnalyzerService.checkHandForStraight(hand);
    }
}
