package com.shinzul.blog.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;

public interface NewsRepository extends PagingAndSortingRepository<News, BigInteger>, NewsRepositoryCustom {
	
	public List<News> findByTitle(String title);
	
	public List<News> findByAuthor(User author);
	
	public List<News> findByTags(String tag);
	
	public List<News> findByCategory(Category category);

}
