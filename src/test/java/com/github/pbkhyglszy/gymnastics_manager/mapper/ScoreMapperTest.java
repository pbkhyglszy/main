package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ScoreMapperTest {

    @Autowired
    ScoreMapper scoreMapper;

    @Test
    void add() {
        RefereeScore refereeScore = new RefereeScore();
        refereeScore.setScore(9.5);
        refereeScore.setStatus(false);
        refereeScore.setGroupId(1);
        refereeScore.setAthleteId(1);
        refereeScore.setRefereeId(4);
        scoreMapper.add(refereeScore);
        assertEquals(4, scoreMapper.getAllRSByGroup(1).size());
        assertEquals(9.5, scoreMapper.getAllRSByGroup(1).get(3).getScore());

    }

    @Test
    void delete() {
        scoreMapper.delete(1);
        assertEquals(2, scoreMapper.getAllRSByGroup(1).size());
    }

    @Test
    void getRSId() {
        assertEquals(2, scoreMapper.getRSId(1, 2));
    }

    @Test
    void getStatus() {
        assertTrue(scoreMapper.getStatus(1,2));
    }

    @Test
    void getAllRSByGroup() {
        List<RefereeScore> all = scoreMapper.getAllRSByGroup(1);
        assertEquals(3, all.size());
        assertEquals(9.1, all.get(2).getScore());
    }

    @Test
    void getAllRSByIds() {
    }

    @Test
    void update() {
        RefereeScore refereeScore = new RefereeScore();
        refereeScore.setId(1);
        refereeScore.setScore(9.5);
        refereeScore.setStatus(false);
        refereeScore.setGroupId(1);
        refereeScore.setAthleteId(1);
        refereeScore.setRefereeId(1);
        scoreMapper.update(refereeScore);
        assertEquals(9.5, scoreMapper.getAllRSByGroup(1).get(0).getScore());

    }

    @Test
    void isAllPassed() {
        assertTrue(scoreMapper.isAllPassed(1));
        RefereeScore refereeScore = new RefereeScore();
        refereeScore.setId(1);
        refereeScore.setScore(9);
        refereeScore.setStatus(false);
        refereeScore.setGroupId(1);
        refereeScore.setAthleteId(1);
        refereeScore.setRefereeId(1);
        scoreMapper.update(refereeScore);
        assertFalse(scoreMapper.isAllPassed(1));
    }

    @Test
    void getAllAthleteId() {
    }

    @Test
    void calculateFS() {
    }

    @Test
    void addAllFS() {
    }

    @Test
    void getAllFSByGroup() {
    }

    @Test
    void getAllFSByIds() {
    }
}