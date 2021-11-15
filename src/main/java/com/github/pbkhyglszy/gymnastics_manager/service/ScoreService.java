package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.FinalScore;
import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;

import java.util.Collections;
import java.util.List;

public class ScoreService {
    public void updateRefereeScore(RefereeScore refereeScore){

    }

    public List<RefereeScore> getRefereeScores(int groupId){
        return Collections.emptyList();
    }

    public void auditRefereeScore(boolean status){//总裁判审核，存入FinalScore里

    }

    public List<FinalScore> getFinalScores(int groupId){
        return Collections.emptyList();
    }
}
