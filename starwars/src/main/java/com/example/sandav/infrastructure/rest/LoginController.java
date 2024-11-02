package com.example.sandav.infrastructure.rest;

import com.example.sandav.infrastructure.dto.JWTClient;
import com.example.sandav.infrastructure.dto.UserDto;
import com.example.sandav.infrastructure.jwt.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController implements ILoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @Override
    public ResponseEntity<JWTClient> login(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.getToken(userDto.getUsername());
        JWTClient jwtClient = new JWTClient(token);

        return new ResponseEntity<>(jwtClient, HttpStatus.OK);
    }
}
