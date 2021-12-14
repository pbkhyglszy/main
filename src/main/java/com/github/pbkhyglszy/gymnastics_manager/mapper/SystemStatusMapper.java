package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemStatusMapper {
    int update(int i);
    int get();
    List<Integer> getMaleAthleteIds();
    List<Integer> getFemaleAthleteIds();
    int updateAthleteId(int id, int athleteId);
}
