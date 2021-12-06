package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.FinalScore;
import com.github.pbkhyglszy.gymnastics_manager.entity.RefereeScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
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
        List<Integer> ids = List.of(1,2);
        List<RefereeScore> all = scoreMapper.getAllRSByIds(ids);
        assertEquals(4, all.size());
        assertEquals(9.2, all.get(3).getScore());

        all = scoreMapper.getAllRSByIds(Collections.emptyList());
        assertEquals(0, all.size());
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
        assertEquals(2,scoreMapper.getAllAthleteId(1).length);
        assertEquals(0,scoreMapper.getAllAthleteId(99).length);
    }

    @Test
        void calculateFS() {
        assertEquals(8.966666666666667, scoreMapper.calculateFS(1,1));
    }

    @Test
    void addAllFS() {
        List<FinalScore> fs = new java.util.ArrayList<>(Collections.emptyList());
        FinalScore fs1 = new FinalScore();
        fs1.setId(1);
        fs1.setScore(9);
        fs1.setAthleteId(1);
        fs1.setGroupId(1);
        FinalScore fs2 = new FinalScore();
        fs2.setId(2);
        fs2.setScore(8);
        fs2.setAthleteId(2);
        fs2.setGroupId(1);
        fs.add(fs1);
        fs.add(fs2);

        scoreMapper.addAllFS(fs);
        assertEquals(2, scoreMapper.getAllFSByGroup(1).size());
        assertEquals(9, scoreMapper.getAllFSByGroup(1).get(0).getScore());
        assertEquals(8, scoreMapper.getAllFSByGroup(1).get(1).getScore());
    }

    @Test
    void getAllFSByGroup() {
    }

    @Test
    void getAllFSByIds() {
    }
}