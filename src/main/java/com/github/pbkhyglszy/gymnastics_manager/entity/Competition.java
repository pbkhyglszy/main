package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import lombok.Data;

@Data
public class Competition {
    int id;
    CompetitionType type;
    String eventName;
    Gender gender;
    String ageClassName;
    int minAge;
    int maxAge;
}
