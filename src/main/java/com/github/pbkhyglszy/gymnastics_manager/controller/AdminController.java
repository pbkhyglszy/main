package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.LoginUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.AgeClass;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import com.github.pbkhyglszy.gymnastics_manager.service.GroupService;
import com.github.pbkhyglszy.gymnastics_manager.service.UserService;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    CompetitionService competitionService;
    @DeleteMapping("/admin/age-groups/{groupId}")
    public R<?> deleteAgeGroup(@PathVariable int groupId, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                int result = groupService.deleteGroup(groupId);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    @PostMapping("/admin/age-group")
    public R<?> addAgeGroup(@RequestBody AgeClass ageClass, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                int result = competitionService.addAgeClass(ageClass);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
//    @PostMapping("/admin/create-account")
//    public R<?> createAccount(@RequestBody String Token, @RequestBody String username, @RequestBody String password, @RequestBody PermissionType permissionType) {
//        int result=JwtUtils.verifyToken(Token);
//        if(result==1)return R.error("ExpiredToken", 401);
//        if(result!=0)return R.error("InvalidToken", 401);
//        Map<String, Object> data = JwtUtils.parseToken(Token);
//        if(data.get("permission")>1)
//        {
//            return R.error("AccessDenied", 401);
//        }
//
//    }
}
