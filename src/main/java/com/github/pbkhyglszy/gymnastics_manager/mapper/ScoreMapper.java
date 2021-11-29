package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.FinalScore;
import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
@Mapper
public interface ScoreMapper {

    int add(RefereeScore refereeScore);//RefereeScore表，增

    int delete(int refereeScoreId);//RefereeScore表，删

    int getRSId(int groupId, int refereeId);//查询groupId，refereeId，返回RefereeScore ID

    boolean getStatus(int groupId, int refereeId);//查询groupId，refereeId，返回RefereeScore Status

    List<RefereeScore> getAllRSByGroup(int groupId);//查询groupId，返回所有的RefereeScore

    List<RefereeScore> getAllRSByIds(List<Integer> ids);//TODO:查询groupId，返回所有的RefereeScore

    int update(RefereeScore refereeScore);//RefereeScore表，改

    boolean isAllPassed(int groupId);//判断RefereeScore表所有Status是否通过

    int[] getAllAthleteId(int groupId);//查询groupId，返回athleteId

    double calculateFS(int groupId, int athleteId);//计算平均成绩

    int[] addAllFS(List<FinalScore> finalScores);//FinalScore表，增

    List<FinalScore> getAllFSByGroup(int groupId);//查询groupId，返回所有的FinalScore
    
    List<FinalScore> getAllFSByIds(List<Integer> ids);//TODO:查询groupId，返回所有的FinalScore
}
