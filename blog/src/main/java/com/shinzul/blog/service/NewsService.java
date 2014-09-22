package com.shinzul.blog.service;

import java.math.BigInteger;
import java.util.List;

import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;

public interface NewsService {
	
	public List<News> findAll();
	
	public List<News> findOrderByCreationDatePageable(int page, int size);
	
	public News save(News news);

	public News update(News news);

	public News findById(BigInteger id);
	
	public List<News> findByTitle(String title);
	
	public List<News> findByAuthor(User author);
	
	public List<News> findByTags(String tag);
	
	public List<News> findByCategory(Category category);

	public void delete(BigInteger id);

}
