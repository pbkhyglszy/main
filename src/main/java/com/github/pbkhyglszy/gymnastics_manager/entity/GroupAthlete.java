package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAthlete {
    int id;
    int order;
    int groupId;
    int athleteId;

    public GroupAthlete(int order, int groupId, int athleteId) {
        this.order = order;
        this.groupId = groupId;
        this.athleteId = athleteId;
    }
}
