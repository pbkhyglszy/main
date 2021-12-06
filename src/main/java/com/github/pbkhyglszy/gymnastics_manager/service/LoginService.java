package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.mapper.LoginMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.Random;

@Service
public class LoginService {
    LoginMapper loginMapper;
    String salt = "ThisIsASalt";
    Base64.Encoder b64Encoder = Base64.getEncoder();
    Random Rand= new Random();
    String generatePassword(String account) {
        byte[] token = new byte[10];
        Rand.nextBytes(token);
        return b64Encoder.encodeToString(token);
    }

    void setPassword(int id, String password) {
        loginMapper.setPassword(id, DigestUtils.md5DigestAsHex((password + salt).getBytes()));
    }

    boolean validatePassword(int id, String password) {
        password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        String storedPassword = loginMapper.getPassword(id);
        return storedPassword.equals(password);
    }
    @Deprecated
    String generateToken(int Userid) {
        Random R = new Random(Userid + System.currentTimeMillis() / 1000000);
        byte[] token = new byte[64];
        R.nextBytes(token);
        return b64Encoder.encodeToString(token);
    }
}
