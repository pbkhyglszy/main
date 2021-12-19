package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {//设置代表队名称、账号和缺省密码，增删改查

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private LoginService loginService;

    public int addTeam(Team team) {
        User user = User.builder()
                .name(team.getName())
                .userName(team.getUserName())
                .password(team.getPassword())
                .permission(2)
                .profession(MemberType.TEAM_LEADER)
                .build();
        loginService.generateUser(user);
        team.setUserId(user.getId());
        return teamMapper.add(team);
    }

    public int deleteTeam(int teamId) {
        loginService.loginMapper.deleteUser(teamMapper.getUserId(teamId));
        return teamMapper.delete(teamId);
    }

    public int updateTeam(Team team) {
        User user = User.builder()
                .id(team.getUserId())
                .name(team.getName())
                .userName(team.getUserName())
                .password(loginService.encodePassword(team.getPassword()))
                .permission(2)
                .profession(MemberType.TEAM_LEADER)
                .build();
        if(team.getPassword() == null)
            loginService.loginMapper.updateUser(user);
        else
            loginService.loginMapper.updateUserPassword(user);
        return teamMapper.update(team);
    }

    public List<Team> getTeams() {
        return teamMapper.getAll();
    }

    public Team getTeamById(int teamId){
        return teamMapper.getTeamById(teamId);
    }

}
