package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.Random;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;
    String salt = "ThisIsASalt";
    Base64.Encoder b64Encoder = Base64.getEncoder();
    Random Rand= new Random();
    public String generatePassword(String account) {
        byte[] token = new byte[10];
        Rand.nextBytes(token);
        return b64Encoder.encodeToString(token);
    }

    public int setPassword(int id, String password) {
        return loginMapper.setPassword(id, encodePassword(password));
    }

    public boolean validatePassword(int id, String password) {
        password = encodePassword(password);
        String storedPassword = loginMapper.getPassword(id);
        return storedPassword.equals(password);
    }
    @Deprecated
    public String generateToken(int Userid) {
        Random R = new Random(Userid + System.currentTimeMillis() / 1000000);
        byte[] token = new byte[64];
        R.nextBytes(token);
        return b64Encoder.encodeToString(token);
    }

    public Integer getIdByUsername(String username)
    {
        return loginMapper.getIdByUsername(username);
    }
    private int calcPermission(MemberType type)
    {
        switch (type)
        {
            case ADMIN:
                return 1;
            case REFEREE:
                return 3;
            case TEAM_LEADER:
                return 2;
        }
        return 5;
    }
    public int generateUser(User user)
    {
        user.setPermission(calcPermission(user.getProfession()));
        user.setPassword(encodePassword(user.getPassword()));
        return loginMapper.createUser(user);
    }

    public String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }
}
