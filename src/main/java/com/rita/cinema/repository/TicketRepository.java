package com.rita.cinema.repository;

import com.rita.cinema.domain.Seance;
import com.rita.cinema.domain.Ticket;
import com.rita.cinema.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByLineAndPlaceAndSeance(int line, int place, Seance seance);
    List<Ticket> findAllByUser(User user);
}
