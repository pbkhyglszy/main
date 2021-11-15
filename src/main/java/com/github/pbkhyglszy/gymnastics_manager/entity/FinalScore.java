package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class FinalScore {
    int id;
    double score;
    int athleteId;
    int groupId;
}
