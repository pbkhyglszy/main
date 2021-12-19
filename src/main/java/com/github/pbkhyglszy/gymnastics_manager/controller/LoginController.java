package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.service.LoginService;
import com.github.pbkhyglszy.gymnastics_manager.service.UserService;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    String key = "ThisIsASecretKey";
    SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    @PostMapping("/login")
    public R<?> login(@RequestBody User user) {



        Integer userId = loginService.getIdByUsername(user.getUsername());
        if(userId==null)return R.error("错误的用户名", 401);
        if(loginService.validatePassword(userId, user.getPassword()))
        {
            HashMap<String, Object> data = new HashMap<>();
            data.put("userName", user.getUsername());
            data.put("permission",userService.getPermission(userId));
            return R.ok(JwtUtils.createToken(data));
        }
        return R.error("用户名或密码错误", 401);
    }

    public int validateToken(String Token)
    {
        return JwtUtils.verifyToken(Token);
    }
//    public R<?> validatePermission(String Token,int permission)
//    {
//        int result= validateToken(Token);
//        if(result==)
//    }
}
