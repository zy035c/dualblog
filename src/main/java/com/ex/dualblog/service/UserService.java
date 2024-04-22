package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.User;


public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User findUserByEmailAndPassword(User user);
    User findUserByID(String ID);
    String userLogin(User user);
    boolean userLogout(String token);
    boolean findTokenbyUUID(String uuid);
    void userDelete(String uuid);

    // boolean ESAddUser(User user);
}
