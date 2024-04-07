package com.ex.dualblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ex.dualblog.model.User;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    void addUser(User user);
    User findUserByEmailAndPassword(User user);
    User findUserByID(String id);
}
