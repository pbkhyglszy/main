package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.*;
import com.github.pbkhyglszy.gymnastics_manager.service.*;
import com.github.pbkhyglszy.gymnastics_manager.vo.ArrangementGroup;
import com.github.pbkhyglszy.gymnastics_manager.vo.CompetitionsResult;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import com.github.pbkhyglszy.gymnastics_manager.vo.TeamDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class GuestController {
    @Autowired
    CompetitionService competitionService;

    @Autowired
    TeamService teamService;

    @Autowired
    SystemStatusService systemStatusService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    GroupService groupService ;
    @GetMapping("/all-events")
    public R<?> getAllEvents() {
        return R.ok(competitionService.getEvents());
    }

    @GetMapping("/all-age-groups")
    public R<?> getAllAgeGroups() {
        return R.ok(competitionService.getAgeClasses());
    }

    @GetMapping("/all-grouped-competitions")
    public R<?> getAllGroupedCompetitions() {
        List<Competition> competitions = competitionService.getCompetitions();
        HashMap<Integer, CompetitionsResult> result = new HashMap<>();
        for (Competition i : competitions) {
            if (!result.containsKey(i.getEventId())) {
                result.put(i.getEventId(), new CompetitionsResult(i));
            }
            result.get(i.getEventId()).addGroup(new CompetitionsResult.Entry(i, competitionService.getSignedNumber(i.getEventId(), i.getAgeClassId())));
        }
        return R.ok(result.values());
    }

    @GetMapping("/all-teams")
    public R<?> getAllTeams() {
        return R.ok(teamService.getTeams());
    }

    @GetMapping("/progress")
    public R<?> getProgress() {
        return R.ok(systemStatusService.getStatus("system_status"));
    }

    @GetMapping("/team-detail/{teamId}")
    public R<?> getTeamDetail(@PathVariable int teamId) {
        Team team = teamService.getTeamById(teamId);
        TeamDetail r= new TeamDetail(team);
        r.setMembers(registrationService.getTeamMembers(team.getId()));
        return R.ok(r);
    }
    @GetMapping("/competition-groups/{competitionId}")
    public R<?> getCompetitionGroups(@PathVariable int competitionId)
    {
        List<ArrangementGroup> ret= new ArrayList<>();
        List<Group> groups=groupService.getGroups(competitionId);
        for (Group i:groups
             ) {
            ArrangementGroup t=new ArrangementGroup();
            t.setGroupId(i.getId());
            t.setGroupName(i.getGroupName());
            List<Athlete> athletesByGroup = groupService.getAthletesByGroup(i.getId());
            for (Athlete it1:athletesByGroup
                 ) {
                t.addAthletes(it1);
            }
            List<TeamMember> refereesByGroup = groupService.getRefereesByGroup(i.getId());
            for (TeamMember it2:refereesByGroup
                 ) {
                t.addReferee(it2);
            }
//            t.setAthletes(groupService.));
            ret.add(t);
        }
        return R.ok(ret);
    }
}
