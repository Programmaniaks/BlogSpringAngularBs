package com.shinzul.blog.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.shinzul.blog.dao.UserRepository;
import com.shinzul.blog.entity.User;
import com.shinzul.blog.service.UserService;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return Lists.newArrayList(getUserRepository().findAll());
	}

	@Override
	public User save(User user) {
		return getUserRepository().save(user);
	}

	@Override
	public User update(User user) {
		return getUserRepository().save(user);
	}

	@Override
	public User findById(BigInteger id) {
		return getUserRepository().findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return getUserRepository().findByUsername(username);
	}

	@Override
	public List<User> findByEmail(String email) {
		return getUserRepository().findByEmail(email);
	}

	@Override
	public void delete(BigInteger id) {
		getUserRepository().delete(id);
	}

}
