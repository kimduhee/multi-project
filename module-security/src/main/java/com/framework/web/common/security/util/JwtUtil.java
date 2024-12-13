package com.framework.web.common.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtil {

    public static String createJWT(String userId, String secretKey) {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(userId)) {
            return null;
        }

        return Jwts.builder()
                .subject("myToken")
                .expiration(new Date(System.currentTimeMillis()+(1000 * 60 * 10)))
                .issuedAt(new Date())
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS256, getSignInKey(secretKey))
                .compact();
    }

    public static String validateToken(String token, String secretKey) {
        try {
            String userId = (String)Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId");
            return userId;
        } catch(Exception e) {
            log.error("validateToken error : {}", e);
            return "";
        }
    }

    private static Key getSignInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
