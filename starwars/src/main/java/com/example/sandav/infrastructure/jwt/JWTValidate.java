package com.example.sandav.infrastructure.jwt;

import com.example.sandav.infrastructure.service.CustomDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.example.sandav.infrastructure.jwt.Constants.*;

public class JWTValidate {
    public static boolean tokenExists(HttpServletRequest request,
                                      HttpServletResponse response) {
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            return false;
        }
        return true;
    }

    public static Claims JWTValid(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder().setSigningKey(getSignedKey(SUPER_SECRET_KEY))
                .build().parseClaimsJws(jwtToken).getBody();
    }

    public static void setAuthentication(Claims claims, CustomDetailService customDetailService) {
        UserDetails userDetails = customDetailService.loadUserByUsername(claims.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }
}
