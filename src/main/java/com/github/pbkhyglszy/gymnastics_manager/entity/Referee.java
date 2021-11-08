package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class Referee {//裁判
    int id;
    int teamId;
    String name;
    String idNumber;
    String phone;
}
