package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CompetitionMapper {
    //    List<Test> getAll();

    int addNewCompetition(Competition competition) throws SQLException;

    void deleteCompetitionById(int id) throws SQLException;

    void modifyCompetitionById(int id, Competition competition) throws SQLException;

    List<Competition> getCompetitionList();

    Competition getCompetitionById(int id);

    /**
     Todo:根据传入的模式串对象决定筛选条件 若某个属性非NULL 则选择与该属性相同的所有Competition
     */
    List<Competition> getCompetitionByCondition(Competition pattern);
}
