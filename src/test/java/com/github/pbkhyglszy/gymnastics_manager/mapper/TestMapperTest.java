package com.github.pbkhyglszy.gymnastics_manager.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TestMapperTest {

    @Autowired
    TestMapper testMapper;

    @Test
    void getAll() {
        List<com.github.pbkhyglszy.gymnastics_manager.entity.Test> all = testMapper.getAll();
        assertEquals(2, all.size());

    }
}