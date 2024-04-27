package com.ex.dualblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ex.dualblog.model.Blog;

@Mapper
public interface BlogMapper {
    List<Blog> getAllBlogs();

    void addBlog(Blog blog);
}
