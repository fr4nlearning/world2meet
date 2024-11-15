package com.example.sandav.domain.port;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import org.springframework.data.domain.Pageable;

public interface IStarshipRepository {
    Starship save(Starship starship);

    ResponseListPageable<Starship> getAllStarshipPageable(Pageable pageable);

    Iterable<Starship> getAllStarshipByName(String name);

    Starship findById(Integer id);

    void deleteById(Integer id);
}
