package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.AgeClass;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.Event;
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
    public int deleteCompetition(int competitionId) {
        return competitionMapper.deleteCompetitionById(competitionId);
    }

    @SneakyThrows
    public int updateCompetition(Competition competition) {
        return competitionMapper.modifyCompetitionById(competition);
    }

    @SneakyThrows
    public List<Competition> getCompetitions() {
        return competitionMapper.getCompetitionList();
    }

    public Competition getCompetition(int id) {
        return competitionMapper.getCompetitionById(id);
    }

    public List<Competition> getCompetitionsByCondition(Competition Pattern) {
        return competitionMapper.getCompetitionsByCondition(Pattern);
    }

    public int addAgeClass(AgeClass ageClass){
        return competitionMapper.addAgeClass(ageClass);
    }
    public int deleteAgeClass(int id){
        return competitionMapper.deleteAgeClass(id);
    }
    public int updateAgeClass(AgeClass ageClass){
        return competitionMapper.updateAgeClass(ageClass);
    }
    public List<AgeClass> getAgeClasses(){
        return competitionMapper.getAllAgeClasses();
    }

    public int addEvent(Event event){
        return competitionMapper.addEvent(event);
    }
    public int deleteEvent(int id){
        return competitionMapper.deleteEvent(id);
    }
    public int updateEvent(Event event){
        return competitionMapper.updateEvent(event);
    }
    public List<Event> getEvents(){
        return competitionMapper.getAllEvents();
    }

}
