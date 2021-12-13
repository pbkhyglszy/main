package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.Coach;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    int addAthlete(Athlete teamMember);
    int addCoach(Coach teamMember);
    int addReferee(TeamMember teamMember);
    int addTeamDoctor(TeamMember teamMember);
    int addTeamLeader(TeamMember teamMember);

    int deleteAthlete(int id);
    int deleteCoach(int id);
    int deleteReferee(int id);
    int deleteTeamDoctor(int id);
    int deleteTeamLeader(int id);

    int updateAthlete(TeamMember teamMember);
    int updateCoach(TeamMember teamMember);
    int updateReferee(TeamMember teamMember);
    int updateTeamDoctor(TeamMember teamMember);
    int updateTeamLeader(TeamMember teamMember);

    List<Athlete> getAthlete(int teamId);
    List<Coach> getCoach(int teamId);
    List<TeamMember> getReferee(int teamId);
    List<TeamMember> getTeamDoctor(int teamId);
    List<TeamMember> getTeamLeader(int teamId);

}
