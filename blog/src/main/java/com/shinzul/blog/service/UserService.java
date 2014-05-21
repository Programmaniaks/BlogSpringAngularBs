package com.shinzul.blog.service;

import java.math.BigInteger;
import java.util.List;

import com.shinzul.blog.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User save(User user);

	public User update(User user);

	public User findById(BigInteger id);

	public User findByUsername(String username);
	
	public List<User> findByEmail(String email);

	public void delete(BigInteger id);

}
