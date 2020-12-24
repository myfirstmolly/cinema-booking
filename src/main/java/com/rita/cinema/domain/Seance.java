package com.rita.cinema.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@Table(name = "seances")
public final class Seance {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private double price;

    private Date date;

    private boolean is3d;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @JsonBackReference
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Seance(double price, Date date, boolean is3d, Film film, Hall hall) {
        this.price = price;
        this.date = date;
        this.is3d = is3d;
        this.film = film;
        this.hall = hall;
        tickets = new ArrayList<>();
    }
}
