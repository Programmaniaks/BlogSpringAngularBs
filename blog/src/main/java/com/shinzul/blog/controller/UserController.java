package com.shinzul.blog.controller;

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
import com.shinzul.blog.entity.User;
import com.shinzul.blog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@RequestMapping(
			value = "/connect", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User connect(@RequestBody User user) {
		Preconditions.checkNotNull(user);
		Preconditions.checkNotNull(user.getUsername(), "Search name must not be empty");
		Preconditions.checkNotNull(user.getPassword(), "Search name must not be empty");
		Preconditions.checkArgument(!user.getUsername().isEmpty(), "Search name must not be empty");
		Preconditions.checkArgument(!user.getPassword().isEmpty(), "Search name must not be empty");
		return getService().connect(user);
	}
}
