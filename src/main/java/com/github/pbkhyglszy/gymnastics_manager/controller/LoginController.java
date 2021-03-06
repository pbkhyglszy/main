package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.LoginUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.User;
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


        Integer userId = loginService.getIdByUsername(user.getUserName());
        if (userId == null) return R.error("错误的用户名", 101);
        if (loginService.validatePassword(userId, user.getPassword())) {
            HashMap<String, Object> data = new HashMap<>();
            user.setName(userService.getName(userId));
            user.setProfession(userService.getProfession(userId));
            user.setPermission(userService.getPermission(userId));
            user.setId(userId);
            data.put("userName", user.getUserName());
            data.put("permission", userService.getPermission(userId));
            data.put("userType", user.getProfession());
            data.put("userId",user.getId());
            return R.ok(new LoginResult(JwtUtils.createToken(data), user.getUserName(), user.getName(), user.getProfession()));
        }
        return R.error("用户名或密码错误", 101);
    }

    @PostMapping("/user-info")
    public R<?> getUserInfo(String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                return R.ok(JwtUtils.parseToken(token));
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
        });
    }


}
