package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Autowired
    LoginController loginController;
    @Test
    void login() {
        User pb=new User();
        pb.setUsername("pb");
        pb.setPassword("12345678");
        loginController.login(pb);
    }

    @Test
    void getUserInfo() {
    }
}