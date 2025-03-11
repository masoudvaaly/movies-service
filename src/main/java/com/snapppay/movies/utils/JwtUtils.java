package com.snapppay.movies.utils;

import com.snapppay.movies.dto.UserInfo;
import com.snapppay.movies.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.access-token-ttl}")
    private long EXPIRATION_TIME;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username) {
        HashMap<String, String> claims = new HashMap<>();
        claims.put("mobile", username);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000 * 60))
                .signWith(getSecretKey())
                .compact();
    }

    public Authentication getAuthentication(String token) throws CustomException {
        Claims claims = parse(token);
        return new UsernamePasswordAuthenticationToken(extractUserInfo(claims), token, extractGrantedAuthorities(claims));
    }

    private UserInfo extractUserInfo(Claims claims) {
        return UserInfo.builder()
                .mobile(claims.get("mobile", String.class))
                .build();
    }

    private Collection<GrantedAuthority> extractGrantedAuthorities(Claims claims) {
        return Collections.emptyList();
    }

    private Claims parse(String token) throws CustomException {
        try {
            return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ex) {
            throw new CustomException("token.expired", 401);
        }
    }

}