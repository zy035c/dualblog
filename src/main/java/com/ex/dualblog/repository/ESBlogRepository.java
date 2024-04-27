package com.ex.dualblog.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ex.dualblog.model.Car;
import com.ex.dualblog.model.ESBlog;

import java.util.List;

public interface ESBlogRepository extends ElasticsearchRepository<ESBlog, String> {}
