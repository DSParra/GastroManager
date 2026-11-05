package com.restaurant_system.GastroManager.auth.user;

import com.restaurant_system.GastroManager.auth.role.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns =  @JoinColumn(name = "user_id" ),
            inverseJoinColumns = @JoinColumn(name = "role_id" )
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();


}
