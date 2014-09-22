package com.shinzul.blog.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.shinzul.blog.controller",
		"com.shinzul.blog.development" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(
			final List<HttpMessageConverter<?>> converters) {
		converters.add(0, jsonConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	
		Jackson2ObjectMapperFactoryBean testObj = new Jackson2ObjectMapperFactoryBean();
		testObj.setObjectMapper(new ObjectMapper());
		testObj.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
		
		jsonConverter.setObjectMapper(testObj.getObject());
		return jsonConverter;
	}

}
