package com.rita.cinema.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@Table(name = "halls")
public final class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Name can't be empty")
    private String name;

    private int linesNum;

    private int seatsNum;

    @Enumerated(EnumType.STRING)
    private HallType hallType;

    @JsonBackReference
    @OneToMany(mappedBy = "hall", fetch = FetchType.EAGER)
    private List<Seance> seances;

    public Hall(@NotBlank(message = "Name can't be empty") String name,
                int linesNum, int seatsNum, HallType hallType) {
        this.name = name;
        this.linesNum = linesNum;
        this.seatsNum = seatsNum;
        this.hallType = hallType;
        seances = new ArrayList<>();
    }
}
