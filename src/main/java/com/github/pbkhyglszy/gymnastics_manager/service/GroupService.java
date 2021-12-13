package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.dto.GroupDTO;
import com.github.pbkhyglszy.gymnastics_manager.entity.*;
import com.github.pbkhyglszy.gymnastics_manager.mapper.GroupMapper;

import java.util.Collections;
import java.util.List;

public class GroupService {//赛事编排，分组
    private GroupMapper groupMapper;
    public List<Athlete> getAthlete(int competitionId){//返回所有报名该比赛的运动员
        return groupMapper.getAthletesByCompetition(competitionId);
    }

    public List<TeamMember> getReferees(){
        return groupMapper.getReferees();
    }

    public void createGroup(GroupDTO groupDTO){
        groupMapper.createGroup(groupDTO);
    }

    public List<Group> getGroups(int competitionId){
        return groupMapper.getGroupsByCompetition(competitionId);
    }

}
