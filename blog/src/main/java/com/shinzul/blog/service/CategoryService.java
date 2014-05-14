package com.shinzul.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.entity.Category;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getAllCategories() {
		return Lists.newArrayList(getCategoryRepository().findAll());
	}
	
	public Category save(Category category) {
		Preconditions.checkNotNull(category);
		Preconditions.checkNotNull(category.getName());
		return getCategoryRepository().save(category);
	}

}
