package com.example.sandav.domain.model;

import jakarta.validation.constraints.NotNull;

public record Starship(Integer id, @NotNull String name, @NotNull Faction faction) {
}
