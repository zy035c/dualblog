package com.ex.dualblog.model;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "esblog")
public class ESBlog {
    private String content;
    private String title;
    private String author;
    private String id;
    private List<String> tags;

    public ESBlog(String content, String title, String author, String uuid, List<String> tags) {
        this.content = content;
        this.title = title;
        this.author = author;
        this.id = uuid;
        this.tags = tags;
    }

    public ESBlog() {}

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
