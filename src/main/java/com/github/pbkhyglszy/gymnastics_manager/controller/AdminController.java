package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.LoginUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.AgeClass;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.Event;
import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import com.github.pbkhyglszy.gymnastics_manager.service.*;
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

    @Autowired
    TeamService teamService;

    @Autowired
    SystemStatusService systemStatusService;
    /**
     *  删除一整个年龄组
     */
    @DeleteMapping("/admin/age-groups/{groupId}")
    public R<?> deleteAgeGroup(@PathVariable int groupId, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                competitionService.deleteAgeClass(groupId);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    /**
     * 加一个年龄组
     */
    @PostMapping("/admin/age-group")
    public R<?> addAgeGroup(@RequestBody AgeClass ageClass, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                competitionService.addAgeClass(ageClass);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    /**
     * 加一个比赛
     */
    @PostMapping("/admin/event")
    public R<?> addEvent(@RequestBody Event event, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                competitionService.addEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    /**
     * 删一场比赛
     */
    @DeleteMapping("/admin/events/{eventId}")
    public R<?> deleteEvent(@PathVariable int eventId, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                competitionService.deleteEvent(eventId);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    /**
     * 编辑一个年龄组
     */
    @PostMapping("/admin/age-groups/{groupId}")
    public R<?> editAgeGroup(@PathVariable int groupId,@RequestBody AgeClass ageClass, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                assert(ageClass.getId()==groupId);
                competitionService.updateAgeClass(ageClass);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    @PostMapping("/admin/events/{eventId}")
    public R<?> editEvent(@PathVariable int eventId, @RequestBody Event event, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                assert(event.getId()==eventId);
                competitionService.updateEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @PostMapping("/admin/competition")
    public R<?> addCompetition(@RequestBody Competition competition,@RequestHeader("Authorization") String token){
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                competition.setType(CompetitionType.QUALIFICATION);
                competitionService.addCompetition(competition);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @DeleteMapping("/admin/competitions/{competitionId}")
    public R<?> deleteCompetition(@PathVariable int competitionId,@RequestHeader("Authorization") String token){
        return LoginUtils.validatePermission(token, 1, () -> {
            try {

                competitionService.deleteCompetition(competitionId);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @PostMapping("/admin/competitions/{competitionId}")
    public R<?> editCompetition(@PathVariable int competitionId, @RequestBody Competition competition, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                assert(competition.getId()==competitionId);
                competitionService.updateCompetition(competition);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    @PostMapping("/admin/team")
    public R<?> addTeam(@RequestBody Team team, @RequestHeader("Authorization") String token){
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                teamService.addTeam(team);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @DeleteMapping("/admin/teams/{teamId}")
    public R<?> deleteTeam(@PathVariable int teamId,@RequestHeader("Authorization") String token){
        return LoginUtils.validatePermission(token, 1, () -> {
            try {

                teamService.deleteTeam(teamId);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @PostMapping("/admin/teams/{teamId}")
    public R<?> editTeam(@PathVariable int teamId, @RequestBody Team team, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                assert(team.getId()==teamId);
                teamService.updateTeam(team);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
    @PostMapping("/admin/progress")
    public R<?> updateProgress(@RequestParam int progressDlt, @RequestHeader("Authorization") String token)
    {
        if(progressDlt >0) progressDlt = 1;else progressDlt = -1;
        int finalProgress = progressDlt;
        return LoginUtils.validatePermission(token, 1, () -> {
            try {
                int systemStatus=systemStatusService.getStatus("system_status");
                if(systemStatus+ finalProgress >4||systemStatus+ finalProgress <0)
                {
                    return R.error("?", 1);
                }
                systemStatusService.setStatus("system_status", systemStatus+finalProgress);
                if(systemStatus==1)
                {
                    systemStatusService.closeRegistration();
                }
                if(systemStatus+finalProgress==1)
                {
                    systemStatusService.openRegistration();
                }
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
