package com.framework.common.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtil {

    /**
     * JWT create
     * @param userId
     * @param secretKey
     * @return
     */
    public static String createJWT(String userId, String secretKey, String expirationTime) {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(expirationTime)) {
            return null;
        }
        //TODO 사용자의 IP를 넣는것 고려 하자! IP로 JWT 유효성 체크 필요!
        return Jwts.builder()
                .subject("myToken")
                .expiration(new Date(System.currentTimeMillis()+(Integer.parseInt(expirationTime))))
                .issuedAt(new Date())
                .claim("userId", userId)
                .signWith(getSignInKey(secretKey))
                .compact();
    }

    /**
     * JWT validation check
     * @param token
     * @param secretKey
     * @return
     */
    public static String validateToken(String token, String secretKey) {

        try {

            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(secretKey)) {
                return null;
            }

            Jwts.parser().verifyWith(getSignInKey(secretKey)).build().parseSignedClaims(token);
            return "00";
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            if(log.isInfoEnabled()) {
                log.info("* Invalid JWT", e);
            }
            return "01";
        } catch(ExpiredJwtException e) {
            if(log.isInfoEnabled()) {
                log.info("* Expired JWT", e);
            }
            return "02";
        } catch(UnsupportedJwtException e) {
            if(log.isInfoEnabled()) {
                log.info("* Unsupported JWT", e);
            }
            return "03";
        } catch(IllegalArgumentException e) {
            if(log.isInfoEnabled()) {
                log.info("* JWT claims string is empty", e);
            }
            return "04";
        } catch(Exception e) {
            if(log.isInfoEnabled()) {
                log.info("* JWT Exception", e);
            }
            return "99";
        }
    }

    /**
     * Extract userId from JWT
     * @param token
     * @param secretKey
     * @return
     */
    public static String getUserId(String token, String secretKey) {
        try {

            if(StringUtils.isEmpty(token) || StringUtils.isEmpty(secretKey)) {
                return null;
            }

            String userId = (String)Jwts.parser()
                    .verifyWith(getSignInKey(secretKey))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("userId");
            return userId;
        } catch(Exception e) {
            log.error("* getUserId exception", e);
            return "";
        }
    }

    /**
     * 요청 토큰값 조회
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        } else {
            token = "";
        }

        return token;
    }

    private static SecretKey getSignInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
