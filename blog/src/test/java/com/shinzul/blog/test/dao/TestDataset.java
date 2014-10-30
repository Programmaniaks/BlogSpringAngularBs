package com.shinzul.blog.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver.IndexDefinitionHolder;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.shinzul.blog.test.configuration.TestPropertyConfig;

@Component
public class TestDataset {
	
	/**
	 * MongoDB Java client
	 */
	@Autowired
	private Mongo mongo;
	@Autowired
	private MongoDbFactory mongoDbFactory;
	@Autowired
	private MongoMappingContext mongoMappingContext;
	
	/**
	 * MongoDb Database name determined by test resource
	 * {@link TestPropertyConfig}
	 */
	@Value("${datasource.dbname}")
	private String databaseName;
	
	public TestDataset() {
		super();
	}
	
	public void createDatabase() {
		mongo.getDB(databaseName);
	}
	
	public void dropDatabase() {
		mongo.dropDatabase(databaseName);
	}
	
	public void resetDatabase() {
		dropDatabase();
		createDatabase();
	}
	
	public DBCollection getCollection(String collectionName) {
		DB db = mongo.getDB(databaseName);
		return db.getCollection(collectionName);
	}
	
	public void insertInCollection(String collectionName, DBObject... objects) {
		insertInCollection(getCollection(collectionName), objects);
	}
	
	public void insertInCollection(String collectionName, List<DBObject> objects) {
		insertInCollection(getCollection(collectionName), objects);
	}
	
	public void insertInCollection(DBCollection collection, DBObject... objects) {
		collection.insert(objects);
	}
	
	public void insertInCollection(DBCollection collection, List<DBObject> objects) {
		collection.insert(objects);
	}
	
	public DBObject generateDbObjectByMap(Map<String, Object> values) {
		BasicDBObject result = new BasicDBObject();
		values.forEach((key, value) -> {
			result.append(key, value);
			});
		return result;
	}
	
	public void createMongoIndexForClass(Class<?> clazz) {
		MongoPersistentEntityIndexResolver indexResolver = new MongoPersistentEntityIndexResolver(
				mongoMappingContext);
		List<IndexDefinitionHolder> indexes = indexResolver
				.resolveIndexForClass(clazz);
		for (IndexDefinitionHolder indexDefinition : indexes) {
			mongoDbFactory
					.getDb()
					.getCollection(indexDefinition.getCollection())
					.createIndex(indexDefinition.getIndexKeys(),
							indexDefinition.getIndexOptions());
		}
	}

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	public MongoDbFactory getMongoDbFactory() {
		return mongoDbFactory;
	}

	public void setMongoDbFactory(MongoDbFactory mongoDbFactory) {
		this.mongoDbFactory = mongoDbFactory;
	}

	public MongoMappingContext getMongoMappingContext() {
		return mongoMappingContext;
	}

	public void setMongoMappingContext(MongoMappingContext mongoMappingContext) {
		this.mongoMappingContext = mongoMappingContext;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

}
