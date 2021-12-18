package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.enums.PermissionType;
import com.github.pbkhyglszy.gymnastics_manager.service.UserService;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.pbkhyglszy.gymnastics_manager.enums.PermissionType.*;

@RestController
public class AdminController {
    @Autowired
    UserService userService;

//    @PostMapping("/admin/create-account")
//    public R<?> createAccount(@RequestBody String Token, @RequestBody String username, @RequestBody String password, @RequestBody PermissionType permissionType) {
//        int result=JwtUtils.verifyToken(Token);
//        if(result==1)return R.error("ExpiredToken", 401);
//        if(result!=0)return R.error("InvalidToken", 401);
//        Map<String, Object> data = JwtUtils.parseToken(Token);
//        if(data.get("permission")!=ADMIN)
//        {
//            return R.error("AccessDenied", 401);
//        }
//
//    }
}
