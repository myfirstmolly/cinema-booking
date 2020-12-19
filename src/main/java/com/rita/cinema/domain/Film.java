package com.rita.cinema.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    private Date releaseDate;
    private String genre;
    private String summary;
    private String fileName;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @JsonBackReference
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Seance> seances;

    public Film(String name, String director, Date releaseDate, String genre, String summary,
                String fileName, Rating rating) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.summary = summary;
        this.fileName = fileName;
        this.rating = rating;
    }
}
