package com.ex.dualblog.model;

import java.io.Serializable;

public class Blog implements Serializable {
    private Long id = (long) 0;
    private String title;
    private String content;

    // private String author;

    public Blog(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    // public String getAuthor() {
    //     return this.author;
    // }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog() {}

    public String toString() {
        return "Blog{id=" + this.id + ", title=" + this.title + ", blogContent=" + this.content + "}";
    }
}
