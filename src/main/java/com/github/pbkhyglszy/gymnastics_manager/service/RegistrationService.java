package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.*;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.RegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RegistrationService {//代表队报名，增删改查
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private LoginService loginService;

    public int addTeamMember(List<TeamMember> teamMembers) {
        for (TeamMember teamMember : teamMembers
        ) {
            switch (teamMember.getType()) {
                case ATHLETE:
                    Athlete athlete = (Athlete) teamMember;
                    int result = registrationMapper.addAthlete(athlete);
                    //int i = registrationMapper.getAthleteId(athlete);
                    for (int eventId : athlete.getEventIds()
                    ) {
                        registrationMapper.addAthleteEvent(athlete.getId(), eventId);
                    }
                    List<AgeClass> list = registrationMapper.getAllAgeClass();
                    for (AgeClass ageClass:list
                         ) {
                        if (athlete.getAge()>= ageClass.getMinAge() && athlete.getAge() <=ageClass.getMaxAge() )
                            registrationMapper.addAthleteAC(athlete.getId(), ageClass.getId());
                    }
                    return result;
                case COACH:
                    return registrationMapper.addCoach((Coach) teamMember);
                case REFEREE:
                    User user = User.builder()
                            .name(teamMember.getName())
                            .userName(teamMember.getUsername())
                            .password(teamMember.getPassword())
                            .permission(3)
                            .profession(MemberType.TEAM_DOCTOR)
                            .build();
                    loginService.generateUser(user);
                    teamMember.setUserId(user.getId());
                    return registrationMapper.addReferee(teamMember);
                case TEAM_DOCTOR:
                    return registrationMapper.addTeamDoctor(teamMember);
                case TEAM_LEADER:
                    return registrationMapper.addTeamLeader(teamMember);
            }
        }
        return -1;
    }

    public int deleteTeamMember(int teamMemberId, MemberType type) {
        switch (type) {
            case ATHLETE:
                registrationMapper.deleteAthleteAC(teamMemberId);
                registrationMapper.deleteAthleteEvent(teamMemberId);
                return registrationMapper.deleteAthlete(teamMemberId);
            case COACH:
                return registrationMapper.deleteCoach(teamMemberId);
            case REFEREE:
                loginService.loginMapper.deleteUser(registrationMapper.getUserId(teamMemberId));
                return registrationMapper.deleteReferee(teamMemberId);
            case TEAM_DOCTOR:
                return registrationMapper.deleteTeamDoctor(teamMemberId);
            case TEAM_LEADER:
                return registrationMapper.deleteTeamLeader(teamMemberId);
        }
        return -1;
    }

    public int updateTeamMember(TeamMember teamMember, MemberType type) {
        switch (type) {
            case ATHLETE:
                //TODO:一会再加
                return registrationMapper.updateAthlete(teamMember);
            case COACH:
                return registrationMapper.updateCoach(teamMember);
            case REFEREE:
                User user = User.builder()
                        .name(teamMember.getName())
                        .userName(teamMember.getUsername())
                        .password(loginService.encodePassword(teamMember.getPassword()))
                        .permission(3)
                        .profession(MemberType.TEAM_DOCTOR)
                        .build();
                loginService.loginMapper.updateUser(user);
                return registrationMapper.updateReferee(teamMember);
            case TEAM_DOCTOR:
                return registrationMapper.updateTeamDoctor(teamMember);
            case TEAM_LEADER:
                return registrationMapper.updateTeamLeader(teamMember);
        }
        return -1;
    }

    public List<TeamMember> getTeamMembers(int teamId) {
        List<TeamMember> teamMembers = new java.util.ArrayList<>(Collections.emptyList());

        teamMembers.addAll(registrationMapper.getAthlete(teamId));

        teamMembers.addAll(registrationMapper.getCoach(teamId));

        teamMembers.addAll(registrationMapper.getReferee(teamId));

        teamMembers.addAll(registrationMapper.getTeamDoctor(teamId));

        teamMembers.addAll(registrationMapper.getTeamLeader(teamId));

        return teamMembers;
    }

}
