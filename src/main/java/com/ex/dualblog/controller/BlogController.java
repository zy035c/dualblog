package com.ex.dualblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.dualblog.model.Blog;
import com.ex.dualblog.schema.Result;
import com.ex.dualblog.schema.Blog.BlogJsonSchema;
import com.ex.dualblog.schema.Blog.CreateBlogResultSchema;
import com.ex.dualblog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/all",consumes = "application/json", produces = "application/json")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Result<CreateBlogResultSchema> insertBlog(@RequestBody BlogJsonSchema blog) {
        System.out.println("BlogPostController.insertBlog() called");
        System.out.println("BlogPostController.insertBlog() blog title = " + blog.getTitle());
        System.out.println("BlogPostController.insertBlog() blog author = " + blog.getAuthor());

        var schema = blogService.insertBlog(blog);

        return Result.success(schema);
    }

}