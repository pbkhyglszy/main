package com.github.pbkhyglszy.gymnastics_manager.vo;


import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.GroupAthlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.GroupReferee;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrangementGroup {
    int groupId;
    String groupName;
    List<Entry> athletes;
    List<TeamMember> referees;

    public void addAthletes(Athlete athlete) {
        athletes.add(new Entry(athlete));
    }

    public void addReferee(TeamMember teamMember) {
        referees.add(teamMember);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Entry {
        int id;
        String name;
        String athleteId;
        int isChief;
        int order;

        public Entry(Athlete athlete) {
            id = athlete.getId();
            name = athlete.getName();
            athleteId = String.format("%03d", athlete.getAthleteId());
        }


    }

    public List<GroupAthlete> getGroupAthletes() {
        List<GroupAthlete> ret = new ArrayList<>();
        for (Entry i : this.athletes
        ) {
            if (i.athleteId != null) ret.add(new GroupAthlete(i.order, groupId, i.id));
        }
        return ret;
    }

    public List<GroupReferee> getGroupReferees() {
        List<GroupReferee> ret = new ArrayList<>();
        for (Entry i : this.athletes
        ) {
            if (i.athleteId == null) ret.add(new GroupReferee(i.isChief, groupId, i.id));
        }
        return ret;
    }
}
