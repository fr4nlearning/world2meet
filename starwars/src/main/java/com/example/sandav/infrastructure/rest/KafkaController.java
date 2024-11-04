package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.services.KafkaService;
import com.example.sandav.domain.model.Starship;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaController implements IKafkaController {

    private final KafkaService kafkaService;

    @Override
    public ResponseEntity<Void> sendMessage(@RequestBody Starship starship) {
        kafkaService.save(starship);
        return ResponseEntity.ok().build();
    }
}
