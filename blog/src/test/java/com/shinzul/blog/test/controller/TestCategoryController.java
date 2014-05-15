package com.shinzul.blog.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shinzul.blog.configuration.WebConfig;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.service.CategoryService;
import com.shinzul.blog.test.configuration.MockServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServiceConfig.class, WebConfig.class})
@WebAppConfiguration
public class TestCategoryController {

	private MockMvc mockMvc;

	@Autowired
	private CategoryService mockCategoryService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(mockCategoryService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView()
			throws Exception {
		Category first = new Category();
		first.setName("Test");
		
		Category expCat = new Category();
		expCat.setId(new BigInteger("0"));
		expCat.setName("Test");

		when(mockCategoryService.save(first)).thenReturn(expCat);
		
		MvcResult result = mockMvc
				.perform(
						post("/category/save").contentType(
								MediaType.APPLICATION_JSON).content(
										"{\"name\":\"Test\"}"))
				.andExpect(status().isOk())
				.andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		
		Category returnCat = mapper.readValue(result.getResponse().getContentAsString(), Category.class);

		assertEquals(expCat, returnCat);

		verify(mockCategoryService, times(1)).save(first);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

}
