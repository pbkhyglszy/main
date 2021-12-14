package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import lombok.Data;

@Data
public class Event {
    int id;
    String eventName;
    Gender gender;
}
