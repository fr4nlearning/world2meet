package com.example.sandav.infrastructure.adapter;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import com.example.sandav.infrastructure.mapper.StarshipMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class StarshipPaginationRepositoryImpl implements IStarshipRepository {

    private final IStarshipPaginationRepository iStarshipPaginationRepository;
    private final StarshipMapper starshipMapper;

    @Override
    public Starship save(Starship starship) {
        return this.starshipMapper.toStarship(
                iStarshipPaginationRepository.save(this.starshipMapper.toStarshipEntity(starship)));
    }

    @Override
    public ResponseListPageable<Starship> findByNamePageable(String name, Pageable pageable) {

        var pageStarship= this.iStarshipPaginationRepository.findByNamePageable(name, pageable);
        return new ResponseListPageable<Starship>(
                pageStarship.getContent().stream().map(starshipEntity -> starshipMapper.toStarship(starshipEntity)).toList(),
                pageStarship.getTotalElements(),
                pageStarship.getTotalPages(),
                pageStarship.getNumber(),
                pageStarship.getSize()
        );
    }

    @Override
    public Starship findById(Integer id) {
        return this.iStarshipPaginationRepository.findById(id)
                .map(starshipEntity -> starshipMapper.toStarship(starshipEntity))
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        this.iStarshipPaginationRepository.deleteById(id);
    }
}
