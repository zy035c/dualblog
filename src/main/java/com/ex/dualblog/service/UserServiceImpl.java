package com.ex.dualblog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.ex.dualblog.mapper.UserMapper;
import com.ex.dualblog.model.User;
import com.ex.dualblog.utils.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

// import com.ex.dualblog.repository.ESUserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<User> getAllUsers() {
        return UserMapper.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {


        // 检查邮箱是否已被使用
        User u = UserMapper.findUserByEmail(user.getEmail());
        if (u != null) {
            return false;
        }

        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        System.out.println(user);
        user.setPassword(MD5.code(user.getPassword())); // MD5加密密码
        UserMapper.addUser(user);
        return true;
    }

    @Override
    public String userLogin(User user) {
        // 1. 进行一些非空判断
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            throw new CustomException("邮箱不能为空");
        }

        if (user.getPassword() == null || "".equals(user.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        // 2. 从数据库里面根据这个用户名和密码去查询对应的管理员信息，
        user.setPassword(MD5.code(user.getPassword()));
        User u = UserMapper.findUserByEmailAndPassword(user);
        if (u == null) {
            // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
            throw new CustomException("用户名或密码输入错误");
        }
        // 如果查出来了有，那说明确实有这个管理员，而且输入的用户名和密码都对；

        String token = JwtTokenUtils.genToken(u.getId().toString(), user.getPassword());

        redisTemplate.opsForValue().set(u.getId(), token, 2, TimeUnit.HOURS); // 把token加入白名单
        System.out.println("[userLogin] Token: " + token);
        // u.setToken(token);
        // System.out.println((String) redisTemplate.opsForValue().get(u.getId()));
        return token;
    }

    @Override
    public User findUserByEmailAndPassword(User user) {
        return UserMapper.findUserByEmailAndPassword(user);
    }

    @Override
    public User findUserByID(String id) {
        User result = UserMapper.findUserByID(id);
        if (result == null) {
            throw new CustomException("查找的用户不存在");
        }
        return result;
    }

    @Override
    public boolean userLogout(String token) {
        try {
            String uuid = JWT.decode(token).getAudience().get(0);
            return redisTemplate.delete(uuid);
        } catch (Exception e) {
            throw new CustomException("登出错误");
        }
    }

    @Override
    public boolean findTokenbyUUID(String uuid) {
        String value = (String) redisTemplate.opsForValue().get(uuid);
        if (value == null)
            return false;
        else
            return true;
    }

    @Override
    public void userDelete(String token) {
        String id = JWT.decode(token).getAudience().get(0);
        UserMapper.deleteUserByID(id);
    }
}
