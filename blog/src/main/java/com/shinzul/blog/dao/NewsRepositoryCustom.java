package com.shinzul.blog.dao;

import java.util.List;

import com.shinzul.blog.entity.News;

public interface NewsRepositoryCustom {
	
	public List<News> findByTextIndexation(String searchedText);

}
