package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class GroupReferee {
    int id;
    boolean chiefReferee;
    int groupId;
    int refereeId;
}
