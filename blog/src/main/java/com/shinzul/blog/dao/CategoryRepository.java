package com.shinzul.blog.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shinzul.blog.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, BigInteger> {
	
	public List<Category> findByName(String name);

}
