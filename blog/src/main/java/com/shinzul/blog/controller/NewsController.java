package com.shinzul.blog.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/findByPage/{pageId}:{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public List<News> findAll(@PathVariable("pageId") int page, @PathVariable("pageSize") int pageSize) {
		return getNewsService().findOrderByCreationDatePageable(page, pageSize);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public News findById(@PathVariable("id") BigInteger id) {
		return getNewsService().findById(id);
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
