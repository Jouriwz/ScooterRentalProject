package ewa.server.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@UtilityClass
public class JwtUtil {
    byte[] secret = Base64.getDecoder().decode("ef8649bc6e1b743ac6ef9c7e7922d40dc2ed68e38900e519f62b645d383fe832");

    public String createToken(String username){

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
//                .setExpiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        return "Bearer "+token;
    }

    public String verifyToken(String token){

        String foundSubject = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return foundSubject;
    }
}
