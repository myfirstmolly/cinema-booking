package com.rita.cinema.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String password;
    private boolean active;
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private Date birthDate;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    public User(@NotBlank(message = "Name cannot be empty") String name,
                String password, boolean active,
                @Email(message = "Email is not correct")
                @NotBlank(message = "Email cannot be empty") String email,
                Date birthDate) {
        this.name = name;
        this.password = password;
        this.active = active;
        this.email = email;
        this.birthDate = birthDate;
    }
}
