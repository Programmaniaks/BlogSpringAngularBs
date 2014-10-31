package com.shinzul.blog.development;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver.IndexDefinitionHolder;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.shinzul.blog.configuration.DatabaseConfig;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.dao.NewsRepository;
import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;

@Controller
@RequestMapping("/developper")
@Profile(value="dev")
public class DevelopperController {
	
	@Autowired
	private CategoryRepository categoryDao;
	@Autowired
	private NewsRepository newsDao;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private Mongo mongo;
	@Autowired
	private MongoDbFactory mongoDbFactory;
	@Autowired
	private MongoMappingContext mongoMappingContext;
	@Autowired
	private DatabaseConfig databaseConfig;
	
	public DevelopperController() {
		super();
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/resetDatas", method = RequestMethod.GET)
	public void resetDatas() {
		
		mongo.dropDatabase(databaseConfig.getDatabaseName());
		mongo.getDB(databaseConfig.getDatabaseName());
		
		MongoPersistentEntityIndexResolver indexResolver = new MongoPersistentEntityIndexResolver(
				mongoMappingContext);
		List<IndexDefinitionHolder> indexes = indexResolver
				.resolveIndexForClass(News.class);
		for (IndexDefinitionHolder indexDefinition : indexes) {
			mongoDbFactory
					.getDb()
					.getCollection(indexDefinition.getCollection())
					.createIndex(indexDefinition.getIndexKeys(),
							indexDefinition.getIndexOptions());
		}
		
		User user = new User();
		user.setUsername("Shinzul");
		user.setPassword("test");
		user.setEmail("francois.teychene@gmail.com");
		user.setRoles(Lists.newArrayList("ROLE_USER", "ROLE_ADMIN"));
		
		User user2 = new User();
		user2.setUsername("Malika");
		user2.setPassword("test");
		user2.setEmail("charlotte.cavalier@gmail.com");
		user2.setRoles(Lists.newArrayList("ROLE_USER"));
		
		
		Category devCategory = new Category();
		devCategory.setName("Developpement");
		
		Category utilCatgory = new Category();
		utilCatgory.setName("Utils");
		
		News news1 = new News();
		news1.setTitle("Retour sur le JUG Summer camp");
		news1.setContent("Contenue de l'article");
		news1.setAuthor(user);
		news1.setCreationDate(new Date());
		news1.setCategory(devCategory);
		news1.setTags(Lists.newArrayList("Community", "Java"));
		
		News news2 = new News();
		news2.setTitle("Le ServiceLoader Java EE 6");
		news2.setContent("Contenue de l'article");
		news2.setAuthor(user);
		Date customDate = new Date();
		customDate.setYear(2013);
		news2.setCreationDate(customDate);
		news2.setCategory(utilCatgory);
		news2.setTags(Lists.newArrayList("Java", "JEE6"));
		
		categoryDao.save(devCategory);
		categoryDao.save(utilCatgory);
		
		userDao.save(user);
		userDao.save(user2);
		
		newsDao.save(news1);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
		news2.setId(null);
		newsDao.save(news2);
	}

	public CategoryRepository getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryRepository categoryDao) {
		this.categoryDao = categoryDao;
	}

	public NewsRepository getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsRepository newsDao) {
		this.newsDao = newsDao;
	}

	public UserRepository getUserDao() {
		return userDao;
	}

	public void setUserDao(UserRepository userDao) {
		this.userDao = userDao;
	}
	
	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	public DatabaseConfig getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

}
