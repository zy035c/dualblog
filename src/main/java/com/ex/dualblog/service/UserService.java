package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.User;


public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);
}
