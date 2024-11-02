package com.example.sandav.infrastructure.entity;

import com.example.sandav.domain.model.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
