package com.restaurant_system.GastroManager.auth.role;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
