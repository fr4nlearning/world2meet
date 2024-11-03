package com.example.sandav.infrastructure.rest;

import com.example.sandav.domain.model.Starship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/messages")
@Tag(name = "Starship registration Kafka", description = "API to use new Starships through Kafka ")
public interface IKafkaController {
    @Operation(
            summary = "Create a new Starship with Kafka",
            description = "Create a new Starship on the database using Kafka",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User request with name and faction",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Starship.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody Starship starship);
}
