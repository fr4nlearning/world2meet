package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/starship")
@AllArgsConstructor
public class StarshipController {

    private final StarshipService starshipService;

    @PostMapping()
    public Starship save(@RequestBody Starship starship){
        return starshipService.save(starship);
    }

    @GetMapping()
    public ResponseListPageable<Starship> getAllStarship(Pageable pageable){
        return starshipService.findAllStarship(pageable);
    }

    @GetMapping("/{id}")
    public Starship getById(@PathVariable Integer id){
        return starshipService.findById(id);
    }

}
