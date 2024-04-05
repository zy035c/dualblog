package com.ex.dualblog.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String id;
    private String username;
    private String email;
    private String password;
    private String phone;

    // 无参构造函数
    public User() {}

    // 带参数构造函数
    public User(Long id, String username, String email, String password) {
        this.id = Long.toString(id);
        this.username = username;
        this.email = email;
        this.password = password;
    }

     // 带参数构造函数
     public User(String username, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
    }


    // Getters 和 Setters 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // 可选的 toString 方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

