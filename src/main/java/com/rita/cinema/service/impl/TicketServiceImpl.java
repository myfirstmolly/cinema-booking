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
    public Ticket sell(User user, Ticket ticket) {
        if(ticket.getUser() == null)
            ticket.setUser(user);
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
    public void returnTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setUser(null);
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findBySeance(Seance seance) {
        return ticketRepository.findAllBySeanceAndUser(seance, null);
    }
}
