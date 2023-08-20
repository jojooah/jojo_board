package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.common.domain.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private static final String REFRESH_TOKEN = "refresh";
    //토큰 생성
    //HS512 알고리즘을 통해 암호화
    public String createAccessToken(String userId) {
        return Jwts.builder()
                .setSubject("board/user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .claim(CLAIM_NAME_INFO, userId) // 페이로드에는 유저 아이디가 들어간다
                .signWith(Keys.hmacShaKeyFor(Constants.ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS512
                )
                .compact();

    }

    public String createRefreshToken(String uuid) {

        return Jwts.builder()
                .setSubject("board/user")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(604800))) // 일주일
                .claim(REFRESH_TOKEN, uuid) // 고유값이 들어간다
                .signWith(Keys.hmacShaKeyFor(Constants.REFRESH_TOKEN_KEY.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS512
                )
                .compact();
    }


    //토큰을 복호화하여 페이로드에 있는 유저 아이디를 가져온다
    public String getLoginUserId(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Constants.ACCESS_TOKEN_KEY.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(CLAIM_NAME_INFO,String.class);
    }

    //토큰을 복호화하여 페이로드에 있는 리프레쉬 토큰값을 가져온다
    public String getRefreshToken(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Constants.REFRESH_TOKEN_KEY.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(REFRESH_TOKEN,String.class);
    }
}
