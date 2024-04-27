package com.ex.dualblog.service;
import java.util.List;

import com.ex.dualblog.model.User;
import com.ex.dualblog.schema.LoginResultSchema;


public interface UserService {
    List<User> getAllUsers();
    boolean addUser(User user);
    User findUserByEmailAndPassword(User user);
    User findUserByID(String ID);
    LoginResultSchema userLogin(User user);
    boolean userLogout(String token);
    boolean findTokenbyUUID(String uuid);
    void userDelete(String uuid);
    User findUserInfoByToken(String token);
}
