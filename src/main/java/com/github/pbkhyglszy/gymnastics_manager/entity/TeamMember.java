package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import lombok.Data;

@Data
public class TeamMember {
    int id;
    int teamId;
    String name;
    String idNumber;
    String phone;
}
