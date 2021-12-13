package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.dto.GroupDTO;
import com.github.pbkhyglszy.gymnastics_manager.entity.*;

import java.util.Collections;
import java.util.List;

public class GroupService {//赛事编排，分组
    public List<Athlete> getAthlete(int competitionId){//返回所有报名该比赛的运动员
        return Collections.emptyList();
    }

    public List<TeamMember> getReferees(){
        return Collections.emptyList();
    }

    public void createGroup(GroupDTO groupDTO){

    }

    public List<Group> getGroups(int competitionId){
        return Collections.emptyList();
    }

}
