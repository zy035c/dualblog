package com.ex.dualblog.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.dualblog.mapper.UserMapper;
import com.ex.dualblog.model.User;
import com.ex.dualblog.utils.*;

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
        user.setPassword(MD5.code(user.getPassword())); // MD5加密密码
        UserMapper.addUser(user);

        // throw new CustomException("该邮箱已被使用");
    }

    @Override
    public String userLogin(User user){
            // 1. 进行一些非空判断
            if (user.getEmail() == null || "".equals(user.getEmail())) {
                throw new CustomException("邮箱不能为空");
            }
    
            if (user.getPassword() == null || "".equals(user.getPassword())) {
                throw new CustomException("密码不能为空");
            }
            // 2. 从数据库里面根据这个用户名和密码去查询对应的管理员信息，
            User u = UserMapper.findUserByEmailAndPassword(user);
            if (u == null) {
                // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
                throw new CustomException("用户名或密码输入错误");
            }
            // 如果查出来了有，那说明确实有这个管理员，而且输入的用户名和密码都对；
            
            String token = JwtTokenUtils.genToken(u.getId().toString(), user.getPassword());
            // u.setToken(token);
            return token;
    }

    @Override
    public User findUserByEmailAndPassword(User user){
        return UserMapper.findUserByEmailAndPassword(user);
    }


    @Override
    public User findUserByID(String id){
        User result = UserMapper.findUserByID(id);
        if(result == null){
            throw new CustomException("查找的用户不存在");
        }
        return result;
    }
}
