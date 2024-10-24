package com.example.sandav.infrastructure.adapter;

import com.example.sandav.infrastructure.entity.StarshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IStarshipPaginationRepository extends JpaRepository<StarshipEntity, Integer> {

    @Query(value = "SELECT * FROM starships WHERE name LIKE CONCAT('%', :name, '%') OR :name IS NULL", nativeQuery = true)
    Page<StarshipEntity> findByNamePageable(@Param("name") String name, Pageable pageable);

}
