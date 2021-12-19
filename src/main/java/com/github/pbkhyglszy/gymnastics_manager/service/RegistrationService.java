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
                        AthleteEvent athleteEvent = AthleteEvent.builder()
                                .athleteId(athlete.getId())
                                .eventId(eventId)
                                .build();
                        registrationMapper.addAthleteEvent(athleteEvent);
                    }
                    List<AgeClass> list = registrationMapper.getAllAgeClass();
                    for (AgeClass ageClass : list
                    ) {
                        AthleteAgeClass athleteAgeClass = AthleteAgeClass.builder()
                                .athleteId(athlete.getId())
                                .ageClassId(ageClass.getId())
                                .build();
                        if (athlete.getAge() >= ageClass.getMinAge() && athlete.getAge() <= ageClass.getMaxAge())
                            registrationMapper.addAthleteAC(athleteAgeClass);
                    }
                    return result;
                case COACH:
                    return registrationMapper.addCoach((Coach) teamMember);
                case REFEREE:
                    User user = User.builder()
                            .name(teamMember.getName())
                            .userName(teamMember.getUserName())
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
                int result = registrationMapper.deleteReferee(teamMemberId);
                loginService.loginMapper.deleteUser(registrationMapper.getUserId(teamMemberId));
                return result;
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
                Athlete athlete = (Athlete) teamMember;
                registrationMapper.deleteAthleteEvent(athlete.getId());
                for (int eventId : athlete.getEventIds()
                ) {
                    AthleteEvent athleteEvent = AthleteEvent.builder()
                            .athleteId(athlete.getId())
                            .eventId(eventId)
                            .build();
                    registrationMapper.addAthleteEvent(athleteEvent);
                }
                registrationMapper.deleteAthleteAC(athlete.getId());
                List<AgeClass> list = registrationMapper.getAllAgeClass();
                for (AgeClass ageClass : list
                ) {
                    AthleteAgeClass athleteAgeClass = AthleteAgeClass.builder()
                            .athleteId(athlete.getId())
                            .ageClassId(ageClass.getId())
                            .build();
                    if (athlete.getAge() >= ageClass.getMinAge() && athlete.getAge() <= ageClass.getMaxAge())
                        registrationMapper.addAthleteAC(athleteAgeClass);
                }
                return registrationMapper.updateAthlete(teamMember);
            case COACH:
                return registrationMapper.updateCoach(teamMember);
            case REFEREE:
                User user = User.builder()
                        .name(teamMember.getName())
                        .userName(teamMember.getUserName())
                        .password(loginService.encodePassword(teamMember.getPassword()))
                        .permission(3)
                        .profession(MemberType.TEAM_DOCTOR)
                        .build();
                if (teamMember.getPassword() == null)
                    loginService.loginMapper.updateUser(user);
                else
                    loginService.loginMapper.updateUserPassword(user);
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

        List<Athlete> athletes = registrationMapper.getAthlete(teamId);
        for (Athlete a:athletes
             ) {
            a.setEventIds(registrationMapper.getEventIdsByAthlete(a.getId()));

        }
        teamMembers.addAll(athletes);

        teamMembers.addAll(registrationMapper.getCoach(teamId));

        List<TeamMember> referees = registrationMapper.getReferee(teamId);
        for (TeamMember r:referees
        ) {
            r.setUserName(registrationMapper.getUsername(r.getId()));
        }
        teamMembers.addAll(referees);

        teamMembers.addAll(registrationMapper.getTeamDoctor(teamId));

        teamMembers.addAll(registrationMapper.getTeamLeader(teamId));

        return teamMembers;
    }

}
