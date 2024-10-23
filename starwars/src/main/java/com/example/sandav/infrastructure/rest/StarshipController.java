package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/starship")
@AllArgsConstructor
public class StarshipController {

    private final StarshipService starshipService;

    @PostMapping()
    public ResponseEntity<Starship> save(@RequestBody Starship starship){
        return ResponseEntity.ok(starshipService.save(starship));
    }

    @GetMapping()
    public ResponseListPageable<Starship> getAllStarship(Pageable pageable){
        return starshipService.findAllStarship(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Starship> getById(@PathVariable Integer id){
        var ssService= starshipService.findById(id);
        return Objects.nonNull(ssService)?
                ResponseEntity.ok(ssService) :
                ResponseEntity.noContent().build();
    }

}
