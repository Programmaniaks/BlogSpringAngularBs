package com.shinzul.blog.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.shinzul.blog.entity.User;
import com.shinzul.blog.service.UserService;

public class TestUserControllerAsWebservice extends AbstractWebserviceTest {

	@Autowired
	UserService userService;

	@Override
	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		super.setUp();
		Mockito.reset(userService);
	}

	@Test
	public void testConnect() throws Exception {
		User user = new User();
		user.setUsername("Shinzul");
		user.setPassword("azerty01");

		User userReturn = new User();
		userReturn.setUsername("Shinzul");
		userReturn.setPassword("azerty01");
		userReturn.setId(BigInteger.valueOf(123));
		userReturn.setEmail("shinzul@programmaniaks.com");

		when(userService.connect(user)).thenReturn(userReturn);
		String expectedReturn = "{\"id\":" + userReturn.getId() + ",\"username\":\""
				+ userReturn.getUsername() + "\",\"password\":\"" + userReturn.getPassword()
				+ "\",\"email\":\"" + userReturn.getEmail() + "\"}";

		String request = "{\"username\":\"" + user.getUsername() + "\",\"password\":\""
				+ user.getPassword() + "\"}";
		MvcResult result = mockMvc
				.perform(
						post("/user/connect").contentType(MediaType.APPLICATION_JSON).content(
								request)).andExpect(status().isOk()).andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(userService, times(1)).connect(user);
		Mockito.verifyZeroInteractions(userService);
		Assert.assertTrue(true);
	}
}
