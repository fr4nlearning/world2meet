package com.example.sandav.infrastructure.entity;

import com.example.sandav.domain.model.Faction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "starships")
@Data
@NoArgsConstructor
public class StarshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Faction faction;
}
