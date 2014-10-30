package com.shinzul.blog.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.shinzul.blog.configuration.DatabaseConfig;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.dao.NewsRepository;
import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;
import com.shinzul.blog.test.configuration.TestPropertyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestPropertyConfig.class,
		DatabaseConfig.class, TestDataset.class })
public class NewsRepositoryTest {

	@Autowired
	TestDataset dataset;

	@Autowired
	NewsRepository newsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;

	User user;
	Category category;

	/**
	 * Initialize MongoDB with Java client
	 */
	@Before
	public void setUp() throws Exception {
		dataset.resetDatabase();

		User userTmp = new User();
		userTmp.setUsername("username");
		userTmp.setPassword("password");
		user = userRepository.save(userTmp);

		Category categoryTmp = new Category();
		categoryTmp.setName("category");
		category = categoryRepository.save(categoryTmp);
	}

	public void testSave() {
		News news = new News();
		news.setAuthor(user);
		news.setCategory(category);
		news.setContent("undefined content");
		news.setTags(Lists.newArrayList("tag1", "tag2"));
		news.setTitle("TestTitle");

		news = newsRepository.save(news);

		assertNotNull(news.getId());
	}

	public void testModify() {
		News expected = new News();
		expected.setAuthor(user);
		expected.setCategory(category);
		expected.setContent("undefined content");
		expected.setTags(Lists.newArrayList("tag1", "tag2"));
		expected.setTitle("TestTitle");

		expected = newsRepository.save(expected);

		expected.setTitle("ModifiedTitle");
		expected = newsRepository.save(expected);

		News actual = newsRepository.findOne(expected.getId());

		assertEquals(expected, actual);
	}

	/**
	 * Spring Data : Create and search Category in MongoDB
	 */
	@Test
	public void testDBRefExecution() {

		News news = new News();
		news.setAuthor(user);
		news.setCategory(category);
		news.setContent("undefined content");
		news.setTags(Lists.newArrayList("tag1", "tag2"));
		news.setTitle("TestTitle");

		news = newsRepository.save(news);

		news = newsRepository.findOne(news.getId());
		assertEquals(category, news.getCategory());
		assertEquals(user, news.getAuthor());

		category.setName("categoryUpdated");
		categoryRepository.save(category);

		news = newsRepository.findOne(news.getId());
		assertEquals(category, news.getCategory());
	}

	@Test
	public void testFindByTextIndexation_FindOneByTitle() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title1");
		news1 = newsRepository.save(news1);

		List<News> actual = newsRepository.findByTextIndexation("Title1");
		assertEquals(Lists.newArrayList(news1), actual);
	}

	@Test
	public void testFindByTextIndexation_FindMultipleByTitle() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title 1");
		news1 = newsRepository.save(news1);

		News news2 = new News();
		news2.setAuthor(user);
		news2.setCategory(category);
		news2.setContent("Content news2 tag2");
		news2.setTags(Lists.newArrayList("tag2"));
		news2.setTitle("Title 2");
		news2 = newsRepository.save(news2);

		List<News> actual = newsRepository.findByTextIndexation("Title");
		assertEquals(Lists.newArrayList(news1, news2), actual);
	}

	@Test
	public void testFindByTextIndexation_FindOneByContent() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title1");
		news1 = newsRepository.save(news1);

		List<News> actual = newsRepository.findByTextIndexation("news1");
		assertEquals(Lists.newArrayList(news1), actual);
	}

	@Test
	public void testFindByTextIndexation_FindMultipleByContent() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title1");
		news1 = newsRepository.save(news1);

		News news2 = new News();
		news2.setAuthor(user);
		news2.setCategory(category);
		news2.setContent("Content news2");
		news2.setTags(Lists.newArrayList("tag2"));
		news2.setTitle("Title2");
		news2 = newsRepository.save(news2);

		List<News> actual = newsRepository.findByTextIndexation("Content");
		assertEquals(Lists.newArrayList(news1, news2), actual);
	}

	@Test
	public void testFindByTextIndexation_FindOneByTag() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title1");
		news1 = newsRepository.save(news1);

		List<News> actual = newsRepository.findByTextIndexation("tag1");
		assertEquals(Lists.newArrayList(news1), actual);
	}

	@Test
	public void testFindByTextIndexation_FindMultipleByTag() {
		// Regenerate text index in Mongo (due to drop and recreate Mongo
		// database for each tests)
		dataset.createMongoIndexForClass(News.class);

		News news1 = new News();
		news1.setAuthor(user);
		news1.setCategory(category);
		news1.setContent("Content news1");
		news1.setTags(Lists.newArrayList("tag1", "tag2"));
		news1.setTitle("Title1");
		news1 = newsRepository.save(news1);

		News news2 = new News();
		news2.setAuthor(user);
		news2.setCategory(category);
		news2.setContent("Content news2");
		news2.setTags(Lists.newArrayList("tag2"));
		news2.setTitle("Title2");
		news2 = newsRepository.save(news2);

		List<News> actual = newsRepository.findByTextIndexation("tag2");
		assertEquals(Lists.newArrayList(news1, news2), actual);
	}

}
