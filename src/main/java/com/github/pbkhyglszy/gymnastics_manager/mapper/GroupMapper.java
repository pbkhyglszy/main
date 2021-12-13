package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.dto.GroupDTO;
import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.Group;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<Athlete> getAthletesByCompetition(int competitionId);

    List<TeamMember> getReferees();

    void createGroup(GroupDTO groupDTO);

    List<Group> getGroupsByCompetition(int competitionId);
}
