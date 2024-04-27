package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.Blog;
import com.ex.dualblog.schema.Result;
import com.ex.dualblog.schema.Blog.BlogJsonSchema;
import com.ex.dualblog.schema.Blog.CreateBlogResultSchema;


public interface BlogService {
    List<Blog> getAllBlogs();

    CreateBlogResultSchema insertBlog(BlogJsonSchema blog);
}
