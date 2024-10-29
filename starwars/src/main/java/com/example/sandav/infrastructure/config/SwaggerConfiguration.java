package com.example.sandav.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

        info = @Info(
                title = "API Starships",
                description = "Provides a CRUD fo Star Wars ships",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "UNIQUE SERVER",
                        url="http://localhost:8005"
                )
        }
)
public class SwaggerConfiguration {
}
