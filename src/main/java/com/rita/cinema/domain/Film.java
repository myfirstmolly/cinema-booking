package com.rita.cinema.domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@Table(name = "films")
public final class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String director;
    private Integer year;
    private String genre;
    private String summary;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Seance> seances;

    public Film(String name, String director, Integer year, String genre, String summary, Rating rating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.summary = summary;
        this.rating = rating;
        seances = new ArrayList<>();
    }
}
