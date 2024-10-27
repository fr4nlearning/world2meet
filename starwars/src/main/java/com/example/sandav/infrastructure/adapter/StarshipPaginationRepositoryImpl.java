package com.example.sandav.infrastructure.adapter;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import com.example.sandav.infrastructure.mapper.StarshipMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    public ResponseListPageable<Starship> getAllStarshipPageable(Pageable pageable) {

        var page= this.iStarshipPaginationRepository.findAll(pageable);
        return new ResponseListPageable<Starship>(
                page.getContent().stream().map(starshipEntity -> starshipMapper.toStarship(starshipEntity)).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }

    @Override
    public List<Starship> getAllStarshipByName(String name) {

        var listStarship= this.iStarshipPaginationRepository.findAllByNameContaining(name);
        return listStarship.stream().map(starshipEntity -> starshipMapper.toStarship(starshipEntity)).toList();

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
