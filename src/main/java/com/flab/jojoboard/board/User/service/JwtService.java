package com.flab.jojoboard.board.User.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.common.domain.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private static final String CLAIM_NAME_INFO = "userId";

    public String getAccessToken(String userId) {

        return Jwts.builder()
                .setSubject("board/user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .claim(CLAIM_NAME_INFO, userId)
                .signWith(Keys.hmacShaKeyFor(Constants.ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS512
                )
                .compact();
    }

    public Integer getLoginUserId(String token){
        Jwts.parserBuilder().setSigningKey(Constants.ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Constants.ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody();

        return claims.get(CLAIM_NAME_INFO,Integer.class);
    }
}
