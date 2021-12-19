package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> getAll();//获取所有代表队的信息
    int add(Team team);//增代表队
    int delete(int teamId);//删
    int update(Team team);//改
    int getUserId(int teamId);
    Team getTeamById(int teamId);
}
