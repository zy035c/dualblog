package com.ex.dualblog.service;

// import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.dualblog.mapper.BlogMapper;
import com.ex.dualblog.model.Blog;
import com.ex.dualblog.model.ESBlog;
import com.ex.dualblog.repository.ESBlogRepository;
import com.ex.dualblog.schema.BlogJsonSchema;
import com.ex.dualblog.schema.Result;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ESBlogRepository esBlogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        // var l = new ArrayList<Blog>();
        // l.add(new Blog(Long.valueOf(1), "Greeting!", "Hello, World."));
        // return l;
        return blogMapper.getAllBlogs();
    }

    @Override
    public Result<Void> insertBlog(BlogJsonSchema blogJson) {
        
        // 1. Generate UUID
        String uuid = UUID.randomUUID().toString();

        // Check 

        // 2. Insert into db
        try {
            var blogModel = new Blog(uuid, blogJson.getTitle(), blogJson.getAuthorUUID());
            blogMapper.addBlog(blogModel);
        } catch (Exception e) {
            System.out.println("Error during inserting into MySql\n" + e.getMessage());
            return new Result();
        }

        // 3. Insert into Elastic Search
        try {
        ESBlog esBlog = new ESBlog(
            blogJson.getContent(), 
            blogJson.getTitle(), 
            blogJson.getAuthor(), 
            uuid, 
            blogJson.getTags()
        );
        esBlogRepository.save(esBlog);
        } catch (Exception e) {
            System.out.println("Error during inserting into Elastic Search\n" + e.getMessage());
            return false;
        }

        return true;
    }
}