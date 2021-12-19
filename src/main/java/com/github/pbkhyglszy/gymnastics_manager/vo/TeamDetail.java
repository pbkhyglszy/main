package com.github.pbkhyglszy.gymnastics_manager.vo;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class TeamDetail {
    int id;
    String name;
    List<TeamMember> members;

    public TeamDetail(Team team) {
        id=team.getId();
        name=team.getName();
    }
}
