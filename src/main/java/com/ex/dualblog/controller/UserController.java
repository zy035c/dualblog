package com.ex.dualblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ex.dualblog.model.User;
import com.ex.dualblog.schema.LoginSchema;
import com.ex.dualblog.service.UserService;
import com.ex.dualblog.utils.CustomException;
import com.ex.dualblog.utils.*;




@RestController
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService UserService;

    @GetMapping
    public Result<List<User>> getAllUsers() {
        return Result.success(UserService.getAllUsers());
    }
     
    @PostMapping(value = "/create",consumes = "application/json", produces = "application/json")
    public Result<Void> addUser(@RequestBody User user) {
        System.out.println("UserController.addUser() called");
        System.out.println("UserController.addUser() user = " + user);

        System.out.println("UserController.addUser() user.getId() = " + user.getId());
        System.out.println("UserController.addUser() user.getUsername() = " + user.getUsername());
        System.out.println("UserController.addUser() user.getEmail() = " + user.getEmail());
        System.out.println("UserController.addUser() user.getPassword() = " + user.getPassword());
        System.out.println("UserController.addUser() user.getPhone() = " + user.getPhone());
        UserService.addUser(user);

        return Result.success();
    }

    @PostMapping(value = "/login")
    public Result<LoginSchema> userLogin(@RequestBody User user) {

        System.out.println("UserController.userLogin() called");
        System.out.println("UserController.userLogin() user = " + user);

        // print blog's field
        // System.out.println("UserController.userLogin() user.getId() = " + user.getId());
        System.out.println("UserController.userLogin() user.getUsername() = " + user.getUsername());
        System.out.println("UserController.userLogin() user.getEmail() = " + user.getEmail());
        System.out.println("UserController.userLogin() user.getPassword() = " + user.getPassword());
        System.out.println("UserController.userLogin() user.getPhone() = " + user.getPhone());
        
        try {
            String token = UserService.userLogin(user);
            var schema = new LoginSchema(token);
            return Result.success(schema);
        } catch (CustomException e) {
            String msg = e.getErrorMessage();
            if(msg.equals("邮箱不能为空")){
                return Result.error("5000","邮箱不能为空");
            } else if(msg.equals("用户名或密码输入错误")){
                return Result.error("5001","用户名或密码输入错误");
            } else if(msg.equals("密码不能为空")){
                return Result.error("5002","密码不能为空");
            }
        }
        return null;
    }
    
}