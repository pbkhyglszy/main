package com.github.pbkhyglszy.gymnastics_manager.entity;

import io.jsonwebtoken.lang.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilsTest {

    @Test
    void createToken() {
        String name = JwtUtils.createToken(Maps.of("name", (Object) "pb").build());
        Object name1 = JwtUtils.parseToken(name).get("name");
        assertEquals("pb", name1);

    }
}