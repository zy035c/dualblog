package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.Blog;
import com.ex.dualblog.schema.BlogJsonSchema;


public interface BlogService {
    List<Blog> getAllBlogs();

    boolean insertBlog(BlogJsonSchema blog);
}
