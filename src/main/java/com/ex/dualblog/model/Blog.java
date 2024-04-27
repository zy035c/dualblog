package com.ex.dualblog.model;

import java.io.Serializable;

public class Blog implements Serializable {

    private String id;
    private String title;
    private String authorUUID;

    public Blog(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.authorUUID = author;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthorUUID() {
        return this.authorUUID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog() {}

    public String toString() {
        return "Blog{id=" + this.id + ", title=" + this.title + ", blogContent=" + this.content + "}";
    }
}
