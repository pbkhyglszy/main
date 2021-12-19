package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Test
    void validatePassword() {
        boolean b = loginService.validatePassword(11, "2333");
        assertTrue(b);
    }

    @Test
    void generateUser() {
        loginService.generateUser(new User(1, "pb","12345678",0 , MemberType.TEAM_LEADER,"pb"));
    }
}