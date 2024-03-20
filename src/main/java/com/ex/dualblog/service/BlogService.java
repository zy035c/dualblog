package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.Blog;


public interface BlogService {
    List<Blog> getAllBlogs();

    void insertBlog(Blog blog);
}
