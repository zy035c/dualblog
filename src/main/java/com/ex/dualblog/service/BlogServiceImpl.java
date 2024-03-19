package com.ex.dualblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.dualblog.model.Blog;

@Service
public class BlogServiceImpl implements BlogService {

    @Override
    public List<Blog> getAllBlogs() {
        return null;
    }

    @Override
    public void insertBlog(Blog blog) {
        // blogMapper.insertBlog(blog);
    }
}