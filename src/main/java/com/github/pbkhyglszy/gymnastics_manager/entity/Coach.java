package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class Coach {
    int id;
    int teamId;
    int gender;
    String name;
    String idNumber;
    String phone;

}
