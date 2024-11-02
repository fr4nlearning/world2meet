package com.example.sandav.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String SUPER_SECRET_KEY = "74c000dd14f4f807dac5d4d35f888d556a9d4a8d4de059c04455c0979b88bbda";
    public static final long TOKEN_EXPIRATION_TIME = 1500000;

    public static Key getSignedKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
