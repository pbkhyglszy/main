package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.FinalScore;
import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;
import com.github.pbkhyglszy.gymnastics_manager.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     *
     * @param refereeScore 裁判打分
     * @return 成功add的行数
     */
    public int addRefereeScore(RefereeScore refereeScore) {
        return scoreMapper.add(refereeScore);
    }

    /**
     *
     * @param groupId 分组id
     * @param refereeId 裁判id
     * @return 如果不存在未通过的裁判打分，返回true；如果存在，删除该打分，返回false
     */
    public boolean deleteIfNotPass(int groupId, int refereeId) {
        if (getNotPassRSId(groupId, refereeId) == 0) return true;
        else {
            scoreMapper.delete(getNotPassRSId(groupId, refereeId));
            return false;
        }
    }

    /**
     *
     * @param groupId 分组id
     * @param refereeId 裁判id
     * @return 未通过裁判打分
     */
    @Transactional
    protected int getNotPassRSId(int groupId, int refereeId) {
        if (scoreMapper.getStatus(groupId, refereeId)) return 0;
        else {
            return scoreMapper.getRSId(groupId, refereeId);
        }
    }

    public List<RefereeScore> getRefereeScores(int groupId) {
        return scoreMapper.getAllRSByGroup(groupId);
    }

    /**
     *
     * @param refereeScores 裁判打分
     * @param groupId 分组id
     * @return 更新的裁判打分行数；如果group内所有分数的status都通过主裁判审核，算出平均分并add最终成绩
     */
    @Transactional
    public int auditRefereeScore(List<RefereeScore> refereeScores, int groupId) {
        int n = 0;//总裁判审核，存入FinalScore里
        for (RefereeScore refereeScore : refereeScores) {
            n += scoreMapper.update(refereeScore);
        }
        if (scoreMapper.isAllPassed(groupId)) {
            calculateFinalScore(groupId);
        }
        return n;
    }

    /**
     *
     * @param groupId 分组id
     * @return 计算组内所有运动员的平均分，并加入finalscore表
     */
    @Transactional
    protected int calculateFinalScore(int groupId) {
        int[] athleteIds = scoreMapper.getAllAthleteId(groupId);
        List<FinalScore> finalScores = new ArrayList<>();
        for (int athleteId : athleteIds) {
            finalScores.add(FinalScore.builder()
                    .score(scoreMapper.calculateFS(groupId, athleteId))
                    .athleteId(athleteId)
                    .groupId(groupId)
                    .build());
        }
        return scoreMapper.addAllFS(finalScores);
    }

    /**
     *
     * @param groupId 分组id
     * @return 按组给出最终成绩表
     */
    public List<FinalScore> getAllFSByGroup(int groupId) {
        return scoreMapper.getAllFSByGroup(groupId);
    }

    public List<FinalScore> getAllFSByCompetition(int competitionId){
        return scoreMapper.getAllFSByCompetition(competitionId);
    }
}
