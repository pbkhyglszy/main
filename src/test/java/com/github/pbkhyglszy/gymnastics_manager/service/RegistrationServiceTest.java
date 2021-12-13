package com.github.pbkhyglszy.gymnastics_manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class RegistrationServiceTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testObjectMapper() throws JsonProcessingException {
        Athlete athlete = Athlete.builder()
                .athleteId(10)
                .type(MemberType.ATHLETE)
                .id(1)
                .name("pb")
                .idNumber("23333333333333")
                .phone("188888888")
                .teamId(3)
                .competitions(Collections.emptyList())
                .age(20)
                .gender(Gender.FEMALE)
                .build();


        String s = objectMapper.writeValueAsString(athlete);

        TeamMember des = objectMapper.readValue(s, TeamMember.class);
        assertTrue(des instanceof Athlete);
        assertEquals(athlete, des);

    }
}