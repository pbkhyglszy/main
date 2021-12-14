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
    public void getCompetitions() {

    }
}