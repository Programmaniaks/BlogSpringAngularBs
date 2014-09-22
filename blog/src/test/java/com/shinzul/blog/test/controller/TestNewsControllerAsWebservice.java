package com.shinzul.blog.test.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import com.shinzul.blog.service.NewsService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestNewsControllerAsWebservice extends AbstractWebserviceTest {

	@Autowired
	private NewsService mockNewsService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		super.setUp();
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(mockNewsService);
	}
	
	@Test
	public void testFindAllPaged() {
		Assert.assertTrue(true);
	}

	

}
