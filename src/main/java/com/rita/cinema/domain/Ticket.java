package com.rita.cinema.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@Table(name = "tickets")
public final class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private int line;

    private int place;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_id")
    private Seance seance;

    public Ticket(int line, int place, User user, Seance seance) {
        this.line = line;
        this.place = place;
        this.user = user;
        this.seance = seance;
    }
}
