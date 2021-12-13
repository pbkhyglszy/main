package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.mapper.CompetitionMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {//录入比赛，增删改查

    @Autowired
    private CompetitionMapper competitionMapper;

    @SneakyThrows
    public int addCompetition(Competition competition) {
        return competitionMapper.addNewCompetition(competition);
    }

    @SneakyThrows
    public void deleteCompetition(int competitionId) {
        competitionMapper.deleteCompetitionById(competitionId);
    }

    @SneakyThrows
    public void updateCompetition(Competition competition) {
        competitionMapper.modifyCompetitionById(competition.getId(), competition);
    }

    @SneakyThrows
    public List<Competition> getCompetitions() {
        return competitionMapper.getCompetitionList();
    }

    public Competition getCompetition(int id) {
        return competitionMapper.getCompetitionById(id);
    }

    public List<Competition> getCompetitionsByCondition(Competition Pattern) {
        return competitionMapper.getCompetitionByCondition(Pattern);
    }
}
