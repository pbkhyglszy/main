package com.github.pbkhyglszy.gymnastics_manager.vo;

import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.Group;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class CompetitionsResult {
    @Autowired
    CompetitionService competitionService;
    int id;
    List<Entry> groups;
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entry {
        int ageGroupId;
        int competitionId;
        int signedNumber;

        public Entry(Competition competition) {
            ageGroupId=competition.getAgeClassId();
            competitionId=competition.getId();
            signedNumber=10;//TODO:查询报名人数
        }
    }

    public CompetitionsResult(Competition first) {
        id=first.getEventId();
        groups= new ArrayList<>();
        groups.add(new Entry(first));
    }
    public void addGroup(Entry newEntry)
    {
        this.groups.add(newEntry);
    }
}
