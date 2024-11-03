package com.example.sandav.application.services;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class StarshipService {
    private final IStarshipRepository iStarshipRepository;

    @Transactional
    @CacheEvict(value = "starships", allEntries = true)
    public Starship save(Starship starship) {
        return this.iStarshipRepository.save(starship);
    }

    @Cacheable(value = "starships")
    public ResponseListPageable<Starship> getAllStarshipPageable(Pageable pageable) {
        return this.iStarshipRepository.getAllStarshipPageable(pageable);
    }

    @Cacheable(value = "starships")
    public List<Starship> getAllStarshipByName(String name) {
        return this.iStarshipRepository.getAllStarshipByName(name);
    }

    @Cacheable(value = "starships")
    public Starship findById(Integer id) {
        return this.iStarshipRepository.findById(id);
    }

    @CacheEvict(value = "starships", allEntries = true)
    @Transactional
    public Starship update(Integer id, Starship starship) {

        var ssRespository = this.iStarshipRepository.findById(id);
        return Objects.isNull(ssRespository) ?
                null :
                this.iStarshipRepository.save(
                        Starship.builder()
                                .id(ssRespository.getId())
                                .faction(starship.getFaction())
                                .name(starship.getName())
                                .build());
    }

    @CacheEvict(value = "starships", allEntries = true)
    @Transactional
    public void deleteById(Integer id) {
        this.iStarshipRepository.deleteById(id);
    }
}
