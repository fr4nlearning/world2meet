package com.example.sandav.application.services;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IKafkaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KafkaService {
    private final IKafkaRepository iKafkaRepository;

    public void save(Starship starship) {
        this.iKafkaRepository.producer(starship);
    }
}
