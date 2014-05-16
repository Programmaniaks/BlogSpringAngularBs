package com.shinzul.blog.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.shinzul.blog.dao.CategoryRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.service.CategoryService;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAll() {
		return Lists.newArrayList(getCategoryRepository().findAll());
	}
	
	public Category save(Category category) {
		return getCategoryRepository().save(category);
	}

	@Override
	public Category update(Category category) {
		return getCategoryRepository().save(category);
	}

	@Override
	public Category findById(BigInteger id) {
		return getCategoryRepository().findOne(id);
	}

	@Override
	public Category findByName(String name) {
		return getCategoryRepository().findByName(name);
	}

	@Override
	public void delete(BigInteger id) {
		getCategoryRepository().delete(id);
	}

}
