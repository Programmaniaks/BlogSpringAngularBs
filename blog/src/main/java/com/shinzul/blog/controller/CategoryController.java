package com.shinzul.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.shinzul.blog.entity.Category;
import com.shinzul.blog.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	public CategoryService getService() {
		return service;
	}

	public void setService(CategoryService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getAllCategories() {
		return getService().getAllCategories();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category save(@RequestBody Category category) {
		Preconditions.checkNotNull(category);
		return getService().save(category);
	}
	
	
}
