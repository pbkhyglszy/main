package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.FinalScore;
import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;
import com.github.pbkhyglszy.gymnastics_manager.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    public int addRefereeScore(RefereeScore refereeScore) {
        return scoreMapper.add(refereeScore);
    }

    public boolean deleteIfNotPass(int groupId, int refereeId) {
        if (getNotPassRSId(groupId, refereeId) == 0) return true;
        else {
            scoreMapper.delete(getNotPassRSId(groupId, refereeId));
            return false;
        }
    }

    @Transactional
    protected int getNotPassRSId(int groupId, int refereeId) {
        if (scoreMapper.getStatus(groupId, refereeId)) return 0;
        else {
            return scoreMapper.getRSId(groupId, refereeId);
        }
    }

    public List<RefereeScore> getRefereeScores(int groupId) {
        return scoreMapper.getAllRS(groupId);
    }

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

    @Transactional
    protected int[] calculateFinalScore(int groupId) {
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

    public List<FinalScore> getFinalScores(int groupId) {
        return scoreMapper.getAllFS(groupId);
    }
}
