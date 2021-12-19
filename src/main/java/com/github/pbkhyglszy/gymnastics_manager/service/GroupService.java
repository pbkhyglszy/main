package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.*;
import com.github.pbkhyglszy.gymnastics_manager.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired//赛事编排，分组
    private GroupMapper groupMapper;

    public List<Athlete> getAthletes(int eventId, int ageClassId) {//返回所有报名该比赛的运动员
        return groupMapper.getAthletesByCompetition(eventId, ageClassId);
    }

    public List<TeamMember> getReferees() {
        return groupMapper.getReferees();
    }

    public int addGroup(Group group) {
        return groupMapper.addGroup(group);
    }

    public int deleteGroup(int groupId) {
        return groupMapper.deleteGroup(groupId);
    }

    public int updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    public List<Group> getGroups(int competitionId) {
        return groupMapper.getGroupsByCompetition(competitionId);
    }

    public int addAthletesToGroup(List<GroupAthlete> groupAthletes) {
        int result = 0;
        for (GroupAthlete g : groupAthletes
        ) {
            result += groupMapper.addGroupAthlete(g);
        }
        return result;
    }

    public int removeAthletesFromGroup(List<GroupAthlete> groupAthletes) {
        int result = 0;
        for (GroupAthlete g : groupAthletes
        ) {
            result += groupMapper.deleteGroupAthlete(g.getGroupId(), g.getAthleteId());
        }
        return result;
    }

    public List<Athlete> getAthletesByGroup(int groupId) {
        return groupMapper.getAthleteByGroup(groupId);
    }

    public int addRefereesToGroup(List<GroupReferee> groupReferees) {
        int result = 0;
        for (GroupReferee g : groupReferees
        ) {
            result += groupMapper.addGroupReferee(g);
        }
        return result;
    }

    public int removeRefereesFromGroup(List<GroupReferee> groupReferees) {
        int result = 0;
        for (GroupReferee g : groupReferees
        ) {
            result += groupMapper.deleteGroupReferee(g.getGroupId(), g.getRefereeId());
        }
        return result;
    }

    public List<TeamMember> getRefereesByGroup(int groupId) {
        return groupMapper.getRefereeByGroup(groupId);
    }


}
