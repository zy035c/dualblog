package com.ex.dualblog.service;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.dualblog.mapper.BlogMapper;
import com.ex.dualblog.model.Blog;
import com.ex.dualblog.model.ESBlog;
import com.ex.dualblog.repository.ESBlogRepository;
import com.ex.dualblog.schema.Blog.BlogJsonSchema;
import com.ex.dualblog.schema.Blog.CreateBlogResultSchema;
import com.ex.dualblog.utils.CustomException;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ESBlogRepository esBlogRepository;

    @Override
    public List<BlogJsonSchema> getAllBlogs() {
        // var l = new ArrayList<Blog>();
        // l.add(new Blog(Long.valueOf(1), "Greeting!", "Hello, World."));
        // return l;

        var blogs = blogMapper.getAllBlogs();
        var resultList = new ArrayList<BlogJsonSchema>();

        for (var blog : blogs) {
            try {
                var blogJson = makeBlogFromES(blog);
                resultList.add(blogJson);
            } catch (Exception e) {
                System.out.println("Error during generating blog from ES\n" + e.getMessage());
                System.out.println("Blog ID: " + blog.getId() + " Title: " + blog.getTitle() + " will be skipped.");
            }
        }
        System.out.println("Successfully get" + resultList.size() + "blogs.");
        return resultList;
    }

    private BlogJsonSchema makeBlogFromES(Blog blog) {
        var blogJson = new BlogJsonSchema();
        String id = blog.getId();

        // set field fro mysql blog
        blogJson.setAuthorUUID(blog.getAuthorUUID());
        blogJson.setTitle(blog.getTitle());
        blogJson.setTimestamp(blog.getTimestamp());
        blogJson.setId(id);

        // get from es
        var option = esBlogRepository.findById(id);
        if (!option.isPresent()) {
            throw new CustomException("Blog" + id + " " + blog.getTitle() + "储存不完整，获取字段失败");
        }

        ESBlog esBlog = option.get();

        // set firld from es blog
        blogJson.setContent(esBlog.getContent());
        blogJson.setAuthor(esBlog.getAuthor());
        blogJson.setTags(esBlog.getTags());

        return blogJson;
    }

    @Override
    @Transactional
    public CreateBlogResultSchema insertBlog(BlogJsonSchema blogJson) {

        // 1. Generate UUID
        String uuid = UUID.randomUUID().toString();

        // Check
        var res = new CreateBlogResultSchema();
        if (blogJson.getAuthorUUID() == null || blogJson.getAuthor() == "" || blogJson.getAuthor() == null) {
            res.setMsg("博客保存失败，缺少作者信息。");
            res.setOk(false);
            return res;
        }

        // 2. Insert into db
        try {
            var blogModel = new Blog(
                    uuid,
                    blogJson.getTitle(),
                    blogJson.getAuthorUUID(),
                    blogJson.getTimestamp());
            blogMapper.addBlog(blogModel);
        } catch (Exception e) {
            System.out.println("Error during inserting into MySql\n" + e.getMessage());
            res.setMsg("博客保存失败，数据库异常。请联系管理员。");
            res.setOk(false);
            return res;
        }

        // 3. Insert into Elastic Search
        try {
            ESBlog esBlog = new ESBlog(
                    blogJson.getContent(),
                    blogJson.getTitle(),
                    blogJson.getAuthor(),
                    uuid,
                    blogJson.getTags());
            esBlogRepository.save(esBlog);
        } catch (Exception e) {
            System.out.println("Error during inserting into Elastic Search\n" + e.getMessage());
            res.setMsg("博客保存失败，数据库异常。请联系管理员。");
            res.setOk(false);
            return res;
        }

        res.setOk(true);
        return res;
    }

    @Override
    public List<Blog> generateTimeline(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateTimeline'");
    }
}