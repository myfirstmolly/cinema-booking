package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Seance;
import com.rita.cinema.domain.Ticket;
import com.rita.cinema.domain.User;
import com.rita.cinema.repository.TicketRepository;
import com.rita.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket sell(User user, Seance seance, int line, int place) {
        Ticket ticket = new Ticket(line, place, user, seance);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket findByLineAndPlaceAndSeance(int line, int place, Seance seance) {
        return ticketRepository.findByLineAndPlaceAndSeance(line, place, seance);
    }
}
