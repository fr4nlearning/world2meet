package com.example.sandav.application;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class StarshipService {
    private final IStarshipRepository iStarshipRepository;

    public Starship save(Starship starship){
        return this.iStarshipRepository.save(starship);
    }

    public Starship findByName(String name){
        return this.iStarshipRepository.findByName(name);
    }

    public ResponseListPageable<Starship> findAllStarship(Pageable pageable){
        return this.iStarshipRepository.findAllStarship(pageable);
    }

    public Starship findById(Integer id){
        return this.iStarshipRepository.findById(id);
    }
}
