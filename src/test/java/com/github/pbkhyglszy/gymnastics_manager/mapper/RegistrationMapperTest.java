package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.AthleteEvent;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class RegistrationMapperTest {

    @Autowired
    RegistrationMapper registrationMapper;

    @Test
    void addAthlete() {
        Athlete athlete1 = Athlete.builder()
                .athleteId(12)
                .type(MemberType.ATHLETE)
                .name("pb")
                .idNumber("23333333333333")
                .phone("188888888")
                .teamId(3)
                .eventIds(new int[]{1, 2})
                .age(20)
                .gender(Gender.FEMALE)
                .build();
        Athlete athlete2 = Athlete.builder()
                .athleteId(10)
                .type(MemberType.ATHLETE)
                .name("pb2")
                .idNumber("23333333333333")
                .phone("188888888")
                .teamId(3)
                .eventIds(new int[]{1, 2})
                .age(20)
                .gender(Gender.FEMALE)
                .build();
        assertEquals(1, registrationMapper.addAthlete(athlete1));
        assertEquals(1, registrationMapper.addAthlete(athlete2));
        assertEquals(1, registrationMapper.deleteAthlete(1));
        assertEquals(1, registrationMapper.addAthlete(athlete1));

    }

    @Test
    void addTeamLeader() {
    }

    @Test
    void deleteAthlete() {
        registrationMapper.deleteAthlete(10);
        assertEquals(2, registrationMapper.getAthlete(1).size());
        registrationMapper.deleteAthlete(1);
        assertEquals(1, registrationMapper.getAthlete(1).size());
        registrationMapper.deleteAthlete(2);
        assertEquals(0, registrationMapper.getAthlete(1).size());
    }

    @Test
    void deleteTeamLeader() {
    }

    @Test
    void updateAthlete() {
    }

    @Test
    void updateTeamLeader() {
    }

    @Test
    void getAthlete() {
    }

    @Test
    void getTeamLeader() {
    }

    @Test
    void getEventIdsByAthlete() {
        AthleteEvent athleteEvent1 = AthleteEvent.builder()
                .athleteId(28)
                .eventId(2)
                .build();
        AthleteEvent athleteEvent2 = AthleteEvent.builder()
                .athleteId(28)
                .eventId(3)
                .build();
        registrationMapper.addAthleteEvent(athleteEvent1);
        registrationMapper.addAthleteEvent(athleteEvent2);
        assertEquals(5, registrationMapper.getEventIdsByAthlete(28).length);
    }
}