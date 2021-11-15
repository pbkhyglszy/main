package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class RefereeScore {
    int id;
    double score;
    boolean status;
    int groupId;
    int athleteId;
    int refereeId;
}
