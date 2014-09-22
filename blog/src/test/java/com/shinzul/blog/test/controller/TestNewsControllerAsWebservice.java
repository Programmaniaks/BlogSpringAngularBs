package com.shinzul.blog.test.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shinzul.blog.configuration.WebConfig;
import com.shinzul.blog.service.NewsService;
import com.shinzul.blog.test.configuration.MockDatabaseConfig;
import com.shinzul.blog.test.configuration.MockServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServiceConfig.class, MockDatabaseConfig.class, WebConfig.class})
@WebAppConfiguration
public class TestNewsControllerAsWebservice {

	private MockMvc mockMvc;

	@Autowired
	private NewsService mockNewsService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(mockNewsService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	

}
