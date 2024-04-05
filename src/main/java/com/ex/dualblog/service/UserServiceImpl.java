package com.ex.dualblog.service;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.dualblog.mapper.UserMapper;
import com.ex.dualblog.model.User;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;
    @Override
    public List<User> getAllUsers() {
        return UserMapper.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        System.out.println(user);
        UserMapper.addUser(user);
    }
}
