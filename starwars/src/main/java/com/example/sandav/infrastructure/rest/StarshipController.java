package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.services.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.Error;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import com.example.sandav.infrastructure.exception.StarshipNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/starship")
@AllArgsConstructor
public class StarshipController {

    private final StarshipService starshipService;

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
    public ResponseEntity<Starship> save(@RequestBody Starship starship){
        return new ResponseEntity<>(starshipService.save(starship), HttpStatus.CREATED);
    }

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
            @RequestParam(value= "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        var reponseListPageable= starshipService.getAllStarshipPageable(PageRequest.of(page, size));
        return ResponseEntity.ok(reponseListPageable);
    }

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
    public ResponseEntity<List<Starship>> getAllStarshipByName(
            @RequestParam(required = true) String name){
        var listByName= starshipService.getAllStarshipByName(name);
        if (!listByName.isEmpty())
            return ResponseEntity.ok(starshipService.getAllStarshipByName(name));
        else
            throw new StarshipNotFoundException(name);
    }

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
    public ResponseEntity<Starship> getById(@PathVariable Integer id){
        var ssService= starshipService.findById(id);
        if(Objects.nonNull(ssService))
            return ResponseEntity.ok(ssService);
        else
            throw new StarshipNotFoundException(id);
    }

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
    public ResponseEntity<Starship> update(@PathVariable Integer id, @RequestBody Starship starship){
        var ssService= starshipService.update(id, starship);

        if (Objects.nonNull(ssService))
            return ResponseEntity.ok(ssService);
        else
            throw new StarshipNotFoundException(id);
    }

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
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        starshipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
