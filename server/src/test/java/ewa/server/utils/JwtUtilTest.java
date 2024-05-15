package ewa.server.utils;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    @Test
    void createToken() {
        String jwt = JwtUtil.createToken("nickseb");
        assertTrue(jwt.contains("Bearer"));
    }

    @Test
    void verifyToken() {
        String jwt = JwtUtil.createToken("nickseb");
        String username = JwtUtil.verifyToken(jwt.replace("[", "").replace("]", "").replace("Bearer ", "").trim());
        assertTrue(username.contains("nickseb"));
    }
}
