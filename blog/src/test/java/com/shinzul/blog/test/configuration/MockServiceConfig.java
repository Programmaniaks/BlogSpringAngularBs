package com.shinzul.blog.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinzul.blog.service.CategoryService;

@Configuration
public class MockServiceConfig {

	@Bean
	public CategoryService mockCategoryService() {
		return Mockito.mock(CategoryService.class);
	}
	
}
