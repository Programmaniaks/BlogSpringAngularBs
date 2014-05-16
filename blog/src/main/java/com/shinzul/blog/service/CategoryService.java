package com.shinzul.blog.service;

import java.math.BigInteger;
import java.util.List;

import com.shinzul.blog.entity.Category;

public interface CategoryService {

	
	public List<Category> findAll();
	
	public Category save(Category category);

	public Category update(Category category);

	public Category findById(BigInteger id);

	public Category findByName(String name);

	public void delete(BigInteger id);
}
