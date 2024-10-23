package com.example.sandav.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Starship {
    private Integer id;
    private String name;
    private Faction faction;

}