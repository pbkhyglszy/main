package com.github.pbkhyglszy.gymnastics_manager.dto;

import com.github.pbkhyglszy.gymnastics_manager.entity.Group;
import com.github.pbkhyglszy.gymnastics_manager.entity.GroupAthlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.GroupReferee;
import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {
    List<GroupAthlete> groupAthletes;
    List<GroupReferee> groupReferees;
    Group group;
}
