package com.github.pbkhyglszy.gymnastics_manager.vo;

import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
public class CompetitionsResult {
    int id;
    List<Entry> groups;
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Entry {
        int ageClassId;
        int competitionId;
        int signedNumber;
        CompetitionType type;

        public Entry(Competition competition,int signedNumber) {
            ageClassId =competition.getAgeClassId();
            competitionId=competition.getId();
            type=competition.getType();
            this.signedNumber=signedNumber;
        }
    }

    public CompetitionsResult(Competition first) {
        id=first.getEventId();
        groups= new ArrayList<>();
//        groups.add(new Entry(first));
    }
    public void addGroup(Entry newEntry)
    {
        this.groups.add(newEntry);
    }
}
