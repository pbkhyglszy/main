package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import com.github.pbkhyglszy.gymnastics_manager.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {//设置代表队名称、账号和缺省密码，增删改查

    @Autowired
    private TeamMapper teamMapper;

    public int addTeam(Team team) {
        return teamMapper.add(team);
    }

    public int deleteTeam(int teamId) {
        return teamMapper.delete(teamId);
    }

    public int updateTeam(Team team) {
        return teamMapper.update(team);
    }

    public List<Team> getTeams() {
        return teamMapper.getAll();
    }

}
