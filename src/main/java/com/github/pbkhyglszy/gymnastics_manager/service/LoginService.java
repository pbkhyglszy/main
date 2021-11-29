package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.mapper.LoginMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginService {
    LoginMapper loginMapper;
    String salt="ThisIsASalt";
    String generatePassword(String account) {
        //TODO: random string generator
        return "1234";
    }

    void setPassword(int id,String password)
    {
        loginMapper.setPassword(id,password);
    }

    boolean validatePassword(int id,String password)
    {
        password=DigestUtils.md5DigestAsHex((password+salt).getBytes());
        String storedPassword= loginMapper.getPassword(id);
        return storedPassword.equals(password);
    }

    String generateToken(int Userid)
    {
        //TODO: random string generator
        return "1234";
    }
}
