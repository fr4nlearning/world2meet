package com.example.sandav.infrastructure.config;

import com.example.sandav.infrastructure.jwt.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
                aut -> aut
                        .requestMatchers(HttpMethod.GET, "/api/v1/starship/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/starship/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/starship/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/starship/**").hasRole("ADMIN")
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/v1/messages/**").permitAll()
                        .requestMatchers("/api/v1/user/**").permitAll().anyRequest().authenticated()
        ).addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
