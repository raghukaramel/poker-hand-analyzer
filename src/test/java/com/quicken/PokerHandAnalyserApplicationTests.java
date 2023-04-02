package com.quicken;

import static org.assertj.core.api.Assertions.assertThat;

import com.quicken.controller.HandAnalyzerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PokerHandAnalyserApplicationTests {

	@Autowired
	HandAnalyzerController handAnalyzerController;

	@Test
	void contextLoads(){
		assertThat(handAnalyzerController).isNotNull();
	}

}
