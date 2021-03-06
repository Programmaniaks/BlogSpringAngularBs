package com.shinzul.blog.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinzul.blog.service.CategoryService;
import com.shinzul.blog.service.NewsService;
import com.shinzul.blog.service.UserService;

@Configuration
public class MockServiceConfig {

	@Bean
	public CategoryService mockCategoryService() {
		return Mockito.mock(CategoryService.class);
	}
	
	@Bean
	public NewsService mockNewsService() {
		return Mockito.mock(NewsService.class);
	}
	
	@Bean
	public UserService mockUserService() {
		return Mockito.mock(UserService.class);
	}

}
