package com.example.sandav.infrastructure.adapter;

import com.example.sandav.infrastructure.entity.StarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStarshipPaginationRepository extends JpaRepository<StarshipEntity, Integer> {
}
