package com.shinzul.blog.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shinzul.blog.dao.NewsRepository;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.entity.User;
import com.shinzul.blog.service.CategoryService;
import com.shinzul.blog.service.NewsService;
import com.shinzul.blog.service.UserService;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NewsRepository newsRepository;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public NewsRepository getNewsRepository() {
		return newsRepository;
	}

	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public List<News> findAll() {
		return Lists.newArrayList(getNewsRepository().findAll());
	}
	
	@Override
	public List<News> findOrderByCreationDatePageable(int page, int size) {
		PageRequest pageable = new PageRequest(page, size);
		// TODO : Gestion de la creation date
		return Lists.newArrayList(getNewsRepository().findAll(pageable));
	}
	
	@Override
	public News save(News news) {
		Preconditions.checkNotNull(news);
		Preconditions.checkNotNull(news.getAuthor());
		Preconditions.checkNotNull(news.getCategory());
		
		if (news.getAuthor().getId() == null) {
			if (news.getAuthor().getUsername() != null && !news.getAuthor().getUsername().isEmpty()) {
				User user = getUserService().findByUsername(news.getAuthor().getUsername());
				if (user == null) {
					throw new RuntimeException("User "+news.getAuthor().getUsername()+" does not exist");
				}
				news.setAuthor(user);
			} else {
				throw new RuntimeException("Please provide User of the news");
			}
		}
		
		if (news.getCategory().getId() == null) {
			if (news.getCategory().getName() != null && !news.getCategory().getName().isEmpty()) {
				Category category = getCategoryService().findByName(news.getCategory().getName());
				if (category == null) {
					throw new RuntimeException("Category "+news.getCategory().getName()+" does not exist");
				}
				news.setCategory(category);
			} else {
				throw new RuntimeException("Please provide Category of the news");
			}
		}
		
		return getNewsRepository().save(news);
	}
	
	@Override
	public List<News> searchPaged(String tag, int page, int size) {
		return newsRepository.findByTextIndexation(tag, page, size);
	}

	@Override
	public News update(News news) {
		return save(news);
	}

	@Override
	public News findById(BigInteger id) {
		return getNewsRepository().findOne(id);
	}

	@Override
	public List<News> findByTitle(String title) {
		return getNewsRepository().findByTitle(title);
	}

	@Override
	public List<News> findByAuthor(User author) {
		return getNewsRepository().findByAuthor(author);
	}

	@Override
	public List<News> findByTags(String tag) {
		return getNewsRepository().findByTags(tag);
	}

	@Override
	public List<News> findByCategory(Category category) {
		return getNewsRepository().findByCategory(category);
	}

	@Override
	public void delete(BigInteger id) {
		getNewsRepository().delete(id);
	}

}
