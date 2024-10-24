package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @PostMapping()
    public ResponseEntity<Starship> save(@RequestBody Starship starship){
        return new ResponseEntity<>(starshipService.save(starship), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponseListPageable<Starship>> getAllStarship(
            @RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(starshipService.findByNamePageable(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Starship> getById(@PathVariable Integer id){
        var ssService= starshipService.findById(id);
        return Objects.nonNull(ssService)?
                ResponseEntity.ok(ssService) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Starship> update(@PathVariable Integer id, @RequestBody Starship starship){
        var ssService= starshipService.update(id, starship);

        return Objects.nonNull(ssService)?
                ResponseEntity.ok(ssService) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        starshipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
