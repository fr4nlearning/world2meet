package com.example.sandav.infrastructure.rest;

import com.example.sandav.infrastructure.dto.JWTClient;
import com.example.sandav.infrastructure.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
@Tag(name = "Login API", description = "API to manage user login")
public interface ILoginController {
    @Operation(
            summary = "Login",
            description = "Login in the application",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login with username and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JWTClient.class)
                            )

                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<JWTClient> login(@RequestBody UserDto userDto);
}
