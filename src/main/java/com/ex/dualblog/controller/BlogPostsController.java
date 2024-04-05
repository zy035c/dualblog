package com.ex.dualblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ex.dualblog.model.Blog;
import com.ex.dualblog.service.BlogService;



@RestController
@RequestMapping("/blog/posts")
public class BlogPostsController {
    
    @Autowired
    private BlogService blogService;
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String insertBlog(@RequestBody Blog blog) {
        System.out.println("BlogPostController.insertBlog() called");
        System.out.println("BlogPostController.insertBlog() blog = " + blog);

        // print blog's field
        System.out.println("BlogPostController.insertBlog() blog.getId() = " + blog.getId());
        System.out.println("BlogPostController.insertBlog() blog.getTitle() = " + blog.getTitle());
        System.out.println("BlogPostController.insertBlog() blog.getBlogContent() = " + blog.getContent());

        blogService.insertBlog(blog);

        return "ok";
    }

}

