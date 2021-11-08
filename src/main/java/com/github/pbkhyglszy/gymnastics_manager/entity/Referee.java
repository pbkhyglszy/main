package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class Referee {//裁判
    int id,teamId;
    String name,idNumber,phone;
}
