package com.rita.cinema.domain;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_id")
    private Seance seance;
}
