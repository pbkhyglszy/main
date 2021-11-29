package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Team;
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
class TeamMapperTest {

    @Autowired
    TeamMapper teamMapper;

    @Test
    void getAll() {
        List<Team> all = teamMapper.getAll();
        assertEquals(3, all.size());
        assertEquals("diyidui", all.get(0).getAccount());
        assertEquals("第三队", all.get(2).getTeamName());
    }

    @Test
    void add() {
        Team team = new Team();
        team.setTeamName("第四队");
        team.setAccount("disidui");
        team.setPassword("123456");
        teamMapper.add(team);
        assertEquals(4, teamMapper.getAll().size());
    }

    @Test
    void delete() {
        teamMapper.delete(3);
        assertEquals(2, teamMapper.getAll().size());
    }

    @Test
    void update() {
        Team team = new Team();
        team.setId(1);
        team.setTeamName("第四队");
        team.setAccount("disidui");
        team.setPassword("123456");
        teamMapper.update(team);
        assertEquals("第四队",teamMapper.getAll().get(0).getTeamName());
    }
}