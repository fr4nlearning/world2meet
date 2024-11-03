package com.example.sandav.domain.port;

import com.example.sandav.domain.model.Starship;

public interface IKafkaRepository {
    void producer(Starship starship);
}
