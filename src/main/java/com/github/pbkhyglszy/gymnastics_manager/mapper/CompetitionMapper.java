package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.AgeClass;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.Event;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CompetitionMapper {
    //    List<Test> getAll();

    int addNewCompetition(Competition competition) throws SQLException;
    int deleteCompetitionById(int id) throws SQLException;
    int modifyCompetitionById(Competition competition) throws SQLException;
    List<Competition> getCompetitionList();
    Competition getCompetitionById(int id);
    /**
     * Todo:根据传入的模式串对象决定筛选条件 若某个属性非NULL 则选择与该属性相同的所有Competition
     */
    List<Competition> getCompetitionByCondition(Competition pattern);

    int addAgeClass(AgeClass ageClass);
    int deleteAgeClass(int id);
    int updateAgeClass(AgeClass ageClass);
    List<AgeClass> getAllAgeClasses();

    int addEvent(Event event);
    int deleteEvent(int id);
    int updateEvent(Event event);
    List<Event> getAllEvents();
}
