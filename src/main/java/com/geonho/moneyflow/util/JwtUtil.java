package com.geonho.moneyflow.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "ThisIsASecretKeyForJWTThisIsASecretKeyForJWT"; // 256-bit 키 필요
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // ✅ SecretKey 사용

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, Jwts.SIG.HS256) // ✅ 최신 버전 시그니처 적용
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()  // ✅ parserBuilder() → parser() 로 변경
                .verifyWith(key)  // ✅ SecretKey 사용
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key) // ✅ SecretKey 사용
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
