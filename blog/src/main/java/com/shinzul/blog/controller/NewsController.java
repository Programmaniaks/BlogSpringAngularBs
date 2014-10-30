package com.shinzul.blog.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.shinzul.blog.entity.News;
import com.shinzul.blog.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	public NewsController() {
		super();
	}
	
	@RequestMapping(value="/findByPage", method = RequestMethod.GET)
	@ResponseBody
	public List<News> findByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		return getNewsService().findOrderByCreationDatePageable(page, pageSize);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public News findById(@PathVariable("id") BigInteger id) {
		return getNewsService().findById(id);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<News> searchPaged(@RequestParam("searchTag") String tag, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		Preconditions.checkNotNull(tag);
		Preconditions.checkNotNull(page);
		Preconditions.checkNotNull(pageSize);
		return getNewsService().searchPaged(tag, page, pageSize);
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
