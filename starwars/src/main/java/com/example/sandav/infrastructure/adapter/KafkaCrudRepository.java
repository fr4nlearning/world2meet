package com.example.sandav.infrastructure.adapter;

import com.example.sandav.application.services.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IKafkaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KafkaCrudRepository implements IKafkaRepository {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final StarshipService starshipService;

    private final ObjectMapper objectMapper;

    @Override
    public void producer(Starship starship) {
        try {
            String message = objectMapper.writeValueAsString(starship);
            kafkaTemplate.send("springtopic", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "springtopic")
    public void consumer(ConsumerRecord<String, String> message) throws JsonProcessingException {
        String customerJson = message.value();
        Starship starship = objectMapper.readValue(customerJson, Starship.class);
        starshipService.save(starship);

    }
}
