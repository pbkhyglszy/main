package com.github.pbkhyglszy.gymnastics_manager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Test
    void validatePassword() {
        boolean b = loginService.validatePassword(11, "2333");
        assertTrue(b);
    }
}