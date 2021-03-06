package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.Data;

@Data
public class GroupReferee {
    int id;
    boolean chiefReferee;
    int groupId;
    int refereeId;

    public GroupReferee(int isChief, int groupId, int refereeId) {
        this.chiefReferee = isChief!=0;
        this.groupId = groupId;
        this.refereeId = refereeId;
    }
}
