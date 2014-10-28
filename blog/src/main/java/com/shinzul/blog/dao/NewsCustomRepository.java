package com.shinzul.blog.dao;

import java.util.List;

import com.shinzul.blog.entity.News;

public interface NewsCustomRepository {
	
	public List<News> customFindByTextIndexation(String searchedText);

}
