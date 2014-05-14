package com.shinzul.blog.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinzul.blog.dao.CategoryRepository;

@Configuration
public class MockDatabaseConfig {
	
	@Bean
	public CategoryRepository mockCategoryRepository() {
		return Mockito.mock(CategoryRepository.class);
	}

}
