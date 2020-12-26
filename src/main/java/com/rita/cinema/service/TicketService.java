package com.rita.cinema.service;

import com.rita.cinema.domain.Seance;
import com.rita.cinema.domain.Ticket;
import com.rita.cinema.domain.User;

import java.util.List;

public interface TicketService {
    Ticket sell(User user, Ticket ticket);

    List<Ticket> findByUser(User user);

    void deleteById(Long id);

    void returnTicket(Long id);

    List<Ticket> findBySeance(Seance seance);
}

