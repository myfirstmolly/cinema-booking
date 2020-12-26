package com.rita.cinema.service;

import com.rita.cinema.domain.Seance;
import com.rita.cinema.domain.Ticket;
import com.rita.cinema.domain.User;

import java.util.List;

public interface TicketService {
    Ticket sell(User user, Seance seance, int line, int place);

    List<Ticket> findByUser(User user);

    void deleteById(Long id);

    Ticket findByLineAndPlaceAndSeance(int line, int place, Seance seance);
}

