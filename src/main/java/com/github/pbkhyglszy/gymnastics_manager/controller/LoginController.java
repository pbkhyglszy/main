package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.service.LoginService;
import com.github.pbkhyglszy.gymnastics_manager.service.UserService;
import com.github.pbkhyglszy.gymnastics_manager.vo.LoginResult;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

//    String key = "ThisIsASecretKey";
//    SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    @PostMapping("/login")
    public R<?> login(@RequestBody User user) {


        Integer userId = loginService.getIdByUsername(user.getUsername());
        if (userId == null) return R.error("错误的用户名", 101);
        if (loginService.validatePassword(userId, user.getPassword())) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("userName", user.getUsername());
            data.put("permission", userService.getPermission(userId));
            String name = user.getName();
            MemberType type = user.getProfession();
            return R.ok(new LoginResult(JwtUtils.createToken(data), user.getUsername(), name, type));
        }
        return R.error("用户名或密码错误", 101);
    }
    @PostMapping("/user-info")
    public R<?> getUserInfo(String Token) {
        int result = validateToken(Token);
        if (result == 1) return R.error("Token过期", 401);
        if (result != 0) return R.error("错误的Token", 401);
        return R.ok(JwtUtils.parseToken(Token));
    }

    public int validateToken(String Token) {
        return JwtUtils.verifyToken(Token);
    }

    public R<?> validatePermission(String Token, int permission) {
        int result = validateToken(Token);
        if (result == 1) return R.error("Token过期", 401);

        return R.ok();
    }
}
