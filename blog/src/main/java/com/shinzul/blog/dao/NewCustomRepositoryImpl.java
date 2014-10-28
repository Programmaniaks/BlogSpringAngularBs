package com.shinzul.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import com.shinzul.blog.entity.News;

public class NewCustomRepositoryImpl implements NewsCustomRepository {
	
	@Autowired
	private MongoTemplate mongo;
	
	@Override
	public List<News> customFindByTextIndexation(String searchedText) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matchingAny(searchedText);
		TextQuery textQuery = TextQuery.queryText(criteria).sortByScore();
		return getMongo().find(textQuery, News.class);
	}

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}

}
