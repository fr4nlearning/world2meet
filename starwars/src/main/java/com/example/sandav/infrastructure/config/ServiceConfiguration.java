package com.example.sandav.infrastructure.config;

import com.example.sandav.application.StarshipService;
import com.example.sandav.domain.port.IStarshipRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public StarshipService starshipService(IStarshipRepository iStarshipRepository){
        return new StarshipService(iStarshipRepository);
    }
}
