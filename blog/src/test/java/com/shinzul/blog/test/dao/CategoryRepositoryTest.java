package com.shinzul.blog.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.shinzul.blog.configuration.DatabaseConfig;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.test.configuration.TestPropertyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestPropertyConfig.class,
		DatabaseConfig.class, TestDataset.class })
public class CategoryRepositoryTest {

	/**
	 * Spring Data Repository
	 */
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TestDataset testDataset;

	/**
	 * Initialize MongoDB with Java client
	 */
	@Before
	public void setUp() throws Exception {

		testDataset.resetDatabase();
		// Insert documents in collection
		testDataset.insertInCollection("category",
				new BasicDBObject().append("name", "Dev"),
				new BasicDBObject().append("name", "Utils"));
	}

	/**
	 * Spring Data : Create and search Category in MongoDB
	 */
	@Test
	public void testInsert() {

		Category category = new Category();
		category.setName("TestCat");

		categoryRepository.save(category);

		Iterable<Category> iterable = categoryRepository.findAll();

		List<Category> categories = Lists.newArrayList(iterable);

		assertNotNull(category.getId());
		assertEquals(3, categories.size());

	}

	/**
	 * Spring Data : Count Category in MongoDb
	 */
	@Test
	public void testCount() {

		long count = categoryRepository.count();

		assertEquals(2, count);

		Category category = new Category();
		category.setName("TestCat");

		categoryRepository.save(category);

		count = categoryRepository.count();

		assertEquals(3, count);
	}

	/**
	 * Spring Data : Count Category in MongoDb
	 */
	@Test
	public void testDelete() {
		Category category = new Category();
		category.setName("TestCat");

		categoryRepository.save(category);

		BigInteger savedId = category.getId();

		categoryRepository.delete(category);

		Category categoryRetrieved = categoryRepository.findOne(savedId);

		Assert.assertNull(categoryRetrieved);
	}

	@Test
	public void testFindByName() {
		Category result = categoryRepository.findByName("Dev");
		assertNotNull(result);
	}

	/**
	 * Clean MongoDB with Java client
	 */
	@After
	public void after() {
		testDataset.dropDatabase();
	}

}
