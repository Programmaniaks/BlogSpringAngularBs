package com.shinzul.blog.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.dao.NewsRepository;
import com.shinzul.blog.dao.UserRepository;

@Configuration
public class MockDatabaseConfig {
	
	@Bean
	public CategoryRepository mockCategoryRepository() {
		return Mockito.mock(CategoryRepository.class);
	}
	
	@Bean
	public NewsRepository mockNewsRepository() {
		return Mockito.mock(NewsRepository.class);
	}
	
	@Bean
	public UserRepository mockUserRepository() {
		return Mockito.mock(UserRepository.class);
	}

}
