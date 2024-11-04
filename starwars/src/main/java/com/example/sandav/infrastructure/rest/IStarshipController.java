package com.example.sandav.infrastructure.rest;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.Error;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/starship")
@Tag(name = "Starship CRUD", description = "API to manage starships")
public interface IStarshipController {
    @Operation(
            summary = "Create a Starship",
            description = "Create a new Starship on the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Starship request with name and faction",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Starship.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Starship.class)
                            )

                    )
            }
    )
    @PostMapping()
    public ResponseEntity<Starship> save(@RequestBody Starship starship);

    @Operation(
            summary = "Get all Starships",
            description = "Get all Starships with pageable option",
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number",
                            required = false,
                            schema = @Schema(
                                    type = "integer",
                                    defaultValue = "0")),
                    @Parameter(
                            name = "size",
                            description = "Page size",
                            required = false,
                            schema = @Schema(
                                    type = "integer",
                                    defaultValue = "10")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseListPageable.class)
                            )

                    )
            }
    )
    @GetMapping()
    public ResponseEntity<ResponseListPageable<Starship>> getAllStarshipPageable(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size);

    @Operation(
            summary = "Get a Starships by Name",
            description = "Obtain a list of Starships that match the name",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the Starship",
                            required = true,
                            schema = @Schema(type = "string"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful recovery of Starships",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Starship.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Starship not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    )
            }
    )
    @GetMapping("/by-name")
    public ResponseEntity<Iterable<Starship>> getAllStarshipByName(
            @RequestParam(required = true) String name);

    @Operation(
            summary = "Get Starship by Id",
            description = "Obtain a starship by its unique identifier",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Unique Starship identifier",
                            required = true,
                            schema = @Schema(type = "integer")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful obtain of the Starship",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Starship.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Starship not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Starship> getById(@PathVariable Integer id);

    @Operation(
            summary = "Update Starship by Id",
            description = "Updating the details of a starship identified by its unique Id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Unique starship identifier",
                            required = true,
                            schema = @Schema(type = "integer"))
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated Starship object",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Starship.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update of the Starship",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Starship.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Starship not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Starship> update(@PathVariable Integer id, @RequestBody Starship starship);

    @Operation(
            summary = "Delete Starship by Id",
            description = "Deleting a starship identified by its unique Id",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Unique identifier of the Starship",
                            required = true,
                            schema = @Schema(type = "integer"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id);
}
