package com.example.sandav.infrastructure.config;

import com.example.sandav.application.services.KafkaService;
import com.example.sandav.application.services.StarshipService;
import com.example.sandav.application.services.UserService;
import com.example.sandav.domain.port.IKafkaRepository;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public StarshipService starshipService(IStarshipRepository iStarshipRepository) {
        return new StarshipService(iStarshipRepository);
    }

    @Bean
    public UserService userService(IUserRepository iUserRepository) {
        return new UserService(iUserRepository);
    }

    @Bean
    public KafkaService kafkaService(IKafkaRepository iKafkaRepository) {
        return new KafkaService(iKafkaRepository);
    }
}
