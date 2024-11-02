package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.services.StarshipService;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import com.example.sandav.infrastructure.exception.StarshipNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class StarshipController implements IStarshipController {

    private final StarshipService starshipService;

    @Override
    public ResponseEntity<Starship> save(Starship starship) {
        return new ResponseEntity<>(starshipService.save(starship), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseListPageable<Starship>> getAllStarshipPageable(int page, int size) {
        var reponseListPageable = starshipService.getAllStarshipPageable(PageRequest.of(page, size));
        return ResponseEntity.ok(reponseListPageable);
    }

    @Override
    public ResponseEntity<List<Starship>> getAllStarshipByName(String name) {
        var listByName = starshipService.getAllStarshipByName(name);
        if (!listByName.isEmpty())
            return ResponseEntity.ok(starshipService.getAllStarshipByName(name));
        else
            throw new StarshipNotFoundException(name);
    }

    @Override
    public ResponseEntity<Starship> getById(Integer id) {
        var ssService = starshipService.findById(id);
        if (Objects.nonNull(ssService))
            return ResponseEntity.ok(ssService);
        else
            throw new StarshipNotFoundException(id);
    }

    @Override
    public ResponseEntity<Starship> update(Integer id, Starship starship) {
        var ssService = starshipService.update(id, starship);
        if (Objects.nonNull(ssService))
            return ResponseEntity.ok(ssService);
        else
            throw new StarshipNotFoundException(id);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        starshipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
