package com.github.pbkhyglszy.gymnastics_manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class CompetitionServiceTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getCompetitions() throws JsonProcessingException {
        Competition competition = new Competition();
        competition.setId(1);
        competition.setEventName("单杠");
        competition.setGender(Gender.MALE);
        competition.setAgeClassName("7-8");
        competition.setMinAge(7);
        competition.setMaxAge(8);
        competition.setType(CompetitionType.QUALIFICATION);

        String s = objectMapper.writeValueAsString(competition);


    }
}