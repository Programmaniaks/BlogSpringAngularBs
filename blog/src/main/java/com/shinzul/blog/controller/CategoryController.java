package com.shinzul.blog.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Category> findAll() {
		return getService().findAll();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category save(@RequestBody Category category) {
		Preconditions.checkNotNull(category);
		Preconditions.checkArgument(category.getId() == null, "An id already exist, save action will not be executed");
		return getService().save(category);
	}
	
	
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category update(@RequestBody Category category) {
		Preconditions.checkNotNull(category);
		Preconditions.checkNotNull(category.getId(), "An id must exist to update "+category);
		return getService().update(category);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category findById(@PathVariable("id") BigInteger id) {
		Preconditions.checkNotNull(id);
		return getService().findById(id);
	}
	
	@RequestMapping(value="/search&name={name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category findByName(@PathVariable("name")String name) {
		Preconditions.checkNotNull(name);
		Preconditions.checkArgument(!name.isEmpty(), "Search name must not be empty");
		return getService().findByName(name);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") BigInteger id) {
		Preconditions.checkNotNull(id);
		getService().delete(id);
	}
	
}
