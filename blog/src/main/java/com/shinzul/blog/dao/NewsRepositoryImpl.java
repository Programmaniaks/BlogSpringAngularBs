package com.shinzul.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import com.shinzul.blog.entity.News;

public class NewsRepositoryImpl implements NewsRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongo;
	
	@Override
	public List<News> findByTextIndexation(String searchedText, int pageId, int pageSize) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matchingAny(searchedText);
		Query textQuery =  TextQuery.queryText(criteria).sortByScore().skip(pageId * pageSize).limit(pageSize);
		return getMongo().find(textQuery, News.class);
	}

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}

}