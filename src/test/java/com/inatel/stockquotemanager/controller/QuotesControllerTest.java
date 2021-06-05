package com.inatel.stockquotemanager.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuotesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testgetstock() throws Exception {
		URI uri = new URI("/stock");
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				).andExpect(MockMvcResultMatchers
						.status()
						.is(200));
	}

}
