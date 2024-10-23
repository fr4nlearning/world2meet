package com.example.sandav.infrastructure.adapter;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import com.example.sandav.infrastructure.entity.StarshipEntity;
import com.example.sandav.infrastructure.mapper.StarshipMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Starship findByName(String name) {
        return null;
    }

    @Override
    public ResponseListPageable<Starship> findAllStarship(Pageable pageable) {

        Page<StarshipEntity> page= this.iStarshipPaginationRepository.findAll(pageable);
        return new ResponseListPageable<Starship>(
                page.getContent().stream().map(starshipEntity -> starshipMapper.toStarship(starshipEntity)).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }

    @Override
    public Starship findById(Integer id) {
        var ssEntity= this.iStarshipPaginationRepository.findById(id);
        return ssEntity.isEmpty()? null : this.starshipMapper.toStarship(this.iStarshipPaginationRepository.findById(id).get());
    }
}
