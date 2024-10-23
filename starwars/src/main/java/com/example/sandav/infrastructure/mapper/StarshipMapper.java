package com.example.sandav.infrastructure.mapper;

import com.example.sandav.domain.model.Starship;
import com.example.sandav.infrastructure.entity.StarshipEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StarshipMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "name", target = "name"),
                    @Mapping(source = "faction", target = "faction"),
            }
    )
    Starship toStarship(StarshipEntity starshipEntity);

    @InheritInverseConfiguration
    StarshipEntity toStarshipEntity(Starship starship);
}
