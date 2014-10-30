package com.shinzul.blog.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.shinzul.blog.configuration.DatabaseConfig;
import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.User;
import com.shinzul.blog.test.configuration.TestPropertyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestPropertyConfig.class, DatabaseConfig.class, TestDataset.class})
public class UserRepositoryTest {

	/**
	 * Spring Data Repository
	 */
	@Autowired
	UserRepository userRepository;

	@Autowired
	TestDataset testDataset;

	/**
	 * Initialize MongoDB with Java client
	 */
	@Before
	public void setUp() throws Exception {
		testDataset.resetDatabase();
	}

	/**
	 * Spring Data : Create and search Category in MongoDB
	 */
	@Test
	public void testInsert() {

		User user = new User();
		user.setUsername("Test");
		user.setPassword("test");
		user.setEmail("toto@tata.com");

		userRepository.save(user);

		Iterable<User> iterable = userRepository.findAll();

		List<User> categories = Lists.newArrayList(iterable);

		assertNotNull(user.getId());
		assertEquals(1, categories.size());

	}

	@Test
	public void findByUsernameAndPassword() {
		User user = new User();
		user.setUsername("Test");
		user.setPassword("test");
		user.setEmail("toto@gmail.com");

		userRepository.save(user);

		User userFound = userRepository.findByUsernameAndPassword("Test", "test");
		Assert.assertNotNull(userFound);
		Assert.assertEquals(user.getUsername(), userFound.getUsername());
		Assert.assertEquals(user.getPassword(), userFound.getPassword());
		Assert.assertEquals(user.getEmail(), userFound.getEmail());
	}

	@Test
	public void delete() {
		User user = new User();
		user.setUsername("Test");
		user.setPassword("test");
		user.setEmail("toto@gmail.com");

		userRepository.save(user);

		userRepository.delete(user.getId());
		User userFound = userRepository.findOne(user.getId());
		Assert.assertNull(userFound);
	}

	/**
	 * Clean MongoDB with Java client
	 */
	// @After
	public void after() {
		testDataset.dropDatabase();
	}

}
