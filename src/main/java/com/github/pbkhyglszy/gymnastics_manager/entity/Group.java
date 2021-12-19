package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {//一组比赛
    int id;
    int competitionId;
    String groupName;

    public Group(int competitionId) {
        this.competitionId = competitionId;
    }
}
