package com.shinzul.blog.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.google.common.collect.Lists;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.service.CategoryService;

public class TestCategoryControllerAsWebservice extends AbstractWebserviceTest {

	@Autowired
	CategoryService mockCategoryService;

	@Override
	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		super.setUp();
		Mockito.reset(mockCategoryService);
	}

	@Test
	public void testSave() throws Exception {
		Category newObject = new Category();
		newObject.setName("Test");

		when(mockCategoryService.save(newObject)).thenReturn(
				new Category(new BigInteger("0"), "Test"));

		String expectedReturn = "{\"id\":0,\"name\":\"Test\"}";

		MvcResult result = mockMvc
				.perform(
						post("/category/").contentType(MediaType.APPLICATION_JSON).content(
								"{\"name\":\"Test\"}")).andExpect(status().isOk()).andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(mockCategoryService, times(1)).save(newObject);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

	@Test
	public void testUpdate() throws Exception {
		Category newObject = new Category(new BigInteger("0"), "Test1");

		when(mockCategoryService.update(newObject)).thenReturn(
				new Category(new BigInteger("0"), "Test1"));

		String expectedReturn = "{\"id\":0,\"name\":\"Test1\"}";

		MvcResult result = mockMvc
				.perform(
						put("/category/").contentType(MediaType.APPLICATION_JSON).content(
								"{\"id\":0,\"name\":\"Test1\"}")).andExpect(status().isOk())
				.andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(mockCategoryService, times(1)).update(newObject);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

	@Test
	public void testFindAll() throws Exception {
		List<Category> expectedResult = Lists.newArrayList(
				new Category(new BigInteger("0"), "Test"),
				new Category(new BigInteger("0"), "Test"));

		when(mockCategoryService.findAll()).thenReturn(expectedResult);

		String expectedReturn = "[{\"id\":0,\"name\":\"Test\"},{\"id\":0,\"name\":\"Test\"}]";

		MvcResult result = mockMvc.perform(get("/category/all")).andExpect(status().isOk())
				.andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(mockCategoryService, times(1)).findAll();
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

	@Test
	public void testFindById() throws Exception {
		BigInteger testedArgument = new BigInteger("0");
		when(mockCategoryService.findById(testedArgument)).thenReturn(
				new Category(new BigInteger("0"), "Test"));

		String expectedReturn = "{\"id\":0,\"name\":\"Test\"}";

		MvcResult result = mockMvc.perform(get("/category/" + testedArgument.toString()))
				.andExpect(status().isOk()).andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(mockCategoryService, times(1)).findById(testedArgument);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

	@Test
	public void testFindByName() throws Exception {
		String testedArgument = new String("Test");
		when(mockCategoryService.findByName(testedArgument)).thenReturn(
				new Category(new BigInteger("0"), "Test"));

		String expectedReturn = "{\"id\":0,\"name\":\"Test\"}";

		MvcResult result = mockMvc
				.perform(get("/category/search&name=" + testedArgument.toString()))
				.andExpect(status().isOk()).andReturn();

		assertEquals(expectedReturn, result.getResponse().getContentAsString());

		verify(mockCategoryService, times(1)).findByName(testedArgument);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

	@Test
	public void testDelete() throws Exception {
		BigInteger testedArgument = new BigInteger("0");

		mockMvc.perform(delete("/category/" + testedArgument.toString()))
				.andExpect(status().isOk()).andReturn();

		verify(mockCategoryService, times(1)).delete(testedArgument);
		Mockito.verifyZeroInteractions(mockCategoryService);
	}

}
