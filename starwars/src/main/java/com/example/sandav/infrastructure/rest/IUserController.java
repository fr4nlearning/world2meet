package com.example.sandav.infrastructure.rest;

import com.example.sandav.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
public interface IUserController {
    @Operation(
            summary = "Create a new user",
            description = "Create a new user on the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User request with email, password and rol",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )

                    )
            }
    )
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user);
}
