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
import com.ex.dualblog.service.UserService;



@RestController
@RequestMapping("/user/create")

public class UserController {
    
    @Autowired
    private UserService UserService;
    @GetMapping
    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }
     
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addUser(@RequestBody User user) {
        System.out.println("UserController.addUser() called");
        System.out.println("UserController.addUser() user = " + user);

        // print blog's field
        System.out.println("UserController.addUser() user.getId() = " + user.getId());
        System.out.println("UserController.addUser() user.getUsername() = " + user.getUsername());
        System.out.println("UserController.addUser() user.getEmail() = " + user.getEmail());
        System.out.println("UserController.addUser() user.getPassword() = " + user.getPassword());
        System.out.println("UserController.addUser() user.getPhone() = " + user.getPhone());
        UserService.addUser(user);

        return "ok";
    }

}