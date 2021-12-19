package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    LoginController loginController;
    @Test
    void login() {
        User pb=new User();
        pb.setUserName("pb");
        pb.setPassword("12345678");
        loginController.login(pb);
    }

    @Test
    void getUserInfo() {
    }
}