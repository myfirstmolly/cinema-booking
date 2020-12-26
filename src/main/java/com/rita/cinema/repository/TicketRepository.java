package com.rita.cinema.repository;

import com.rita.cinema.domain.Seance;
import com.rita.cinema.domain.Ticket;
import com.rita.cinema.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUser(User user);
    List<Ticket> findAllBySeanceAndUser(Seance seance, User user);
}
