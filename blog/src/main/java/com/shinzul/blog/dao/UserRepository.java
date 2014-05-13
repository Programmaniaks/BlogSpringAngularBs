package com.shinzul.blog.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.shinzul.blog.entity.User;

public interface UserRepository extends CrudRepository<User, BigInteger>{

}
