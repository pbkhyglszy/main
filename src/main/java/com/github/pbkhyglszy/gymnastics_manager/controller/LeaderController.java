package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.LoginUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import com.github.pbkhyglszy.gymnastics_manager.service.RegistrationService;
import com.github.pbkhyglszy.gymnastics_manager.service.SystemStatusService;
import com.github.pbkhyglszy.gymnastics_manager.service.TeamService;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import com.github.pbkhyglszy.gymnastics_manager.vo.TeamDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class LeaderController {
    @Autowired
    CompetitionService competitionService;

    @Autowired
    TeamService teamService;

    @Autowired
    SystemStatusService systemStatusService;

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/qaq")
    public R<?> testFunc(@RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 2, () -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }

    @GetMapping("/team-detail")
    public R<?> getTeamDetail(@RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 2, () -> {
            try {
                Team team = teamService.getTeamByUserId((int) JwtUtils.parseToken(token.substring("Bearer ".length())).get("userId"));
                TeamDetail r = new TeamDetail(team);
                r.setMembers(registrationService.getTeamMembers(team.getId()));
                return R.ok(r);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
        });
    }

    @PostMapping("/team-detail")
    public R<?> updateTeamDetail(@RequestBody TeamDetail teamDetail, @RequestHeader("Authorization") String token) {
        return LoginUtils.validatePermission(token, 2, () -> {
            try {
                int id = (int) JwtUtils.parseToken(token.substring("Bearer ".length())).get("userId");
                Team team = teamService.getTeamByUserId(id);
                int teamId=team.getId();
                if (!(team.getId() == teamDetail.getId())) return R.error("wrongId", 1);
                team.setName(teamDetail.getName());
                teamService.updateTeam(team);
                List<TeamMember> oldMembers = registrationService.getTeamMembers(teamId);
                List<TeamMember> newMembers = teamDetail.getMembers();

                Comparator<TeamMember> teamMemberComparator = Comparator
                        .<TeamMember>comparingInt(it -> it.getType().getValue())
                        .thenComparingInt(TeamMember::getId);
                oldMembers.sort(teamMemberComparator
                );
                newMembers.sort(teamMemberComparator
                );
                int i = oldMembers.size() - 1;
                int j = newMembers.size() - 1;
                for (TeamMember it:oldMembers
                     ) {
                    it.setTeamId(teamId);
                }
                for (TeamMember it:newMembers
                ) {
                    it.setTeamId(teamId);
                }
                while (i > -1 && j > -1) {
                    if (oldMembers.get(i).equals(newMembers.get(j))) {
                        i--;
                        j--;
                        continue;
                    }
                    int compare = teamMemberComparator.compare(oldMembers.get(i), newMembers.get(j));
                    if (compare > 0) {
                        registrationService.deleteTeamMember(oldMembers.get(i).getId(), oldMembers.get(i).getType());
                        i--;
                        continue;
                    }
                    if (compare < 0) {
                        registrationService.addTeamMember(Collections.singletonList(newMembers.get(j)));
                        j--;
                        continue;
                    }
                    registrationService.updateTeamMember(newMembers.get(j), newMembers.get(j).getType());
                    i--;
                    j--;
                }
                while (i > -1) {
                    registrationService.deleteTeamMember(oldMembers.get(i).getId(), oldMembers.get(i).getType());
                    i--;
                }
                while (j > -1) {
                    registrationService.addTeamMember(Collections.singletonList(newMembers.get(j)));
                    j--;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
}
