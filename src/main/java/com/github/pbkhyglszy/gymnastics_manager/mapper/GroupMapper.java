package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.dto.GroupDTO;
import com.github.pbkhyglszy.gymnastics_manager.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<Athlete> getAthletesByEvent(int eventId);

    List<TeamMember> getReferees();

    int addGroup(Group group);
    int deleteGroup(int groupId);
    int updateGroup(Group group);
    List<Group> getGroupsByCompetition(int competitionId);

    int addGroupAthlete(GroupAthlete groupAthlete);
    int deleteGroupAthlete(int groupId, int athleteId);
    List<Athlete> getAthleteByGroup(int groupId);

    int addGroupReferee(GroupReferee groupReferee);
    int deleteGroupReferee(int groupId, int refereeId);
    List<TeamMember> getRefereeByGroup(int groupId);







}
