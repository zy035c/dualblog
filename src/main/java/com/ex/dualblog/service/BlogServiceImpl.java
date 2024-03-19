package com.ex.dualblog.service;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.dualblog.model.Blog;

@Service
public class BlogServiceImpl implements BlogService {

    @Override
    public List<Blog> getAllBlogs() {
        var l = new ArrayList<Blog>();
        l.add(new Blog(Long.valueOf(1), "Greeting!", "Hello, World."));
        return l;
    }

    @Override
    public void insertBlog(Blog blog) {
        // blogMapper.insertBlog(blog);
    }
}