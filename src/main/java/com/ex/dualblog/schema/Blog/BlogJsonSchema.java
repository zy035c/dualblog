package com.ex.dualblog.schema.Blog;

import java.util.List;

import java.sql.Timestamp;

public class BlogJsonSchema {
    private String content;
    private String title;
    private String author;
    private String authorUUID;
    private List<String> tags;

    private Timestamp timestamp;

    public BlogJsonSchema(String content, String title, String author, List<String> tags) {
        this.content = content;
        this.title = title;
        this.author = author;

        this.tags = tags;
    }

    public BlogJsonSchema() {
    }

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

    public String getAuthorUUID() {
        return authorUUID;
    }

    public void setAuthorUUID(String authorUUID) {
        this.authorUUID = authorUUID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
