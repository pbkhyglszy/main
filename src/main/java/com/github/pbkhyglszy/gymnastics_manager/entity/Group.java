package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class Group {//一组比赛
    int id;
    int competitionId;
    int minAge;
    int maxAge;
    int gender;
}
