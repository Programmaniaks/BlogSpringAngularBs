package com.shinzul.blog.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.shinzul.blog.configuration.DatabaseConfig;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.dao.NewsRepository;
import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;
import com.shinzul.blog.test.configuration.TestPropertyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestPropertyConfig.class, DatabaseConfig.class})
public class NewsRepositoryTest {

	@Autowired
	NewsRepository newsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/**
     * MongoDB Java client
     */
    @Autowired
    Mongo mongo;
    
    /**
     * MongoDb Database name determined by test resource {@link TestPropertyConfig}
     */
    @Value("${datasource.dbname}")
    String databaseName;
    
    /**
     * Initialize MongoDB with Java client
     */
    @Before
    public void setUp() throws Exception {
        // Drop database
        mongo.dropDatabase(databaseName);
        // Create database
        mongo.getDB(databaseName);
    }
    
    /**
     * Spring Data : Create and search Category in MongoDB
     */
    @Test
    public void testDBRefExecution() {
 
        User user = new User();
        user.setUsername("Test");
        user.setPassword("test");
        
//        user = userRepository.save(user);
        
        Category cat = new Category();
        cat.setName("TestCat");
//        cat = categoryRepository.save(cat);
        
        News news = new News();
        news.setAuthor(user);
        news.setCategory(cat);
        news.setContent("YOLO");
        news.setTags(Lists.newArrayList("test", "test1"));
        news.setTitle("TestTitle");
        
        news = newsRepository.save(news);
        System.out.println(news);
        
//        user.setUsername("Test2");
//        userRepository.save(user);
    }
}
