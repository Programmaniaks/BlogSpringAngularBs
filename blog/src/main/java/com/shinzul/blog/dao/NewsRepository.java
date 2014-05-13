package com.shinzul.blog.dao;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinzul.blog.entity.News;

public interface NewsRepository extends PagingAndSortingRepository<News, BigInteger>{

}
