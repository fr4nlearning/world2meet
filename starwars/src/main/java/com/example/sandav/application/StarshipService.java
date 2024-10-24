package com.example.sandav.application;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@AllArgsConstructor
public class StarshipService {
    private final IStarshipRepository iStarshipRepository;

    public Starship save(Starship starship){
        return this.iStarshipRepository.save(starship);
    }

    public ResponseListPageable<Starship> findByNamePageable(String name, Pageable pageable){
        return this.iStarshipRepository.findByNamePageable(name, pageable);
    }

    public Starship findById(Integer id){
        return this.iStarshipRepository.findById(id);
    }

    public Starship update(Integer id, Starship starship){

        var ssRespository= this.iStarshipRepository.findById(id);
        return Objects.isNull(ssRespository)?
                null:
                this.iStarshipRepository.save(
                        Starship.builder()
                                .id(ssRespository.getId())
                                .faction(starship.getFaction())
                                .name(starship.getName())
                                .build());
    }

    public void deleteById(Integer id){
        this.iStarshipRepository.deleteById(id);
    }
}
