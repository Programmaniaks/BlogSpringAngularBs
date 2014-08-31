package com.shinzul.blog.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages="com.shinzul.blog.dao")
public class DatabaseConfig {
	
	@Value("${datasource.dbname}")
	private String databaseName;
	
	@Value("${datasource.host}")
	private String databaseHost;
	
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public String getDatabaseHost() {
		return databaseHost;
	}

	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}

	@Bean
	public Mongo mongoClient() throws UnknownHostException {
		return new MongoClient(getDatabaseHost());
	}

	@Bean
	@Autowired
	public MongoDbFactory mongoDbFactory(Mongo mongoClient) throws Exception {
		return new SimpleMongoDbFactory(mongoClient, getDatabaseName());
	}
	
	@Bean
	@Autowired
	public MongoTemplate mongoTemplate(MongoDbFactory dbFactory) throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(dbFactory);
		return mongoTemplate;
	}
	

}
