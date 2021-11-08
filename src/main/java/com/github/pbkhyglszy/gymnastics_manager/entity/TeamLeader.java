package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class TeamLeader {
    int id, teamId;
    String name, idNumber, phone;
}
