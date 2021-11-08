package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class TeamDoctor {
    int id;
    int teamId;
    String name;
    String idNumber;
    String phone;
}
