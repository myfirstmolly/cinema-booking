package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("tickets")
    public String all(@AuthenticationPrincipal User user, Model model) {
        checkUser(user, model);
        model.addAttribute("tickets", ticketService.findByUser(user));
        return "tickets-booked";
    }

    @GetMapping("seance/{seance}")
    public String AllTickets(@PathVariable Seance seance,
                       @AuthenticationPrincipal User user,
                       Model model) {
        checkUser(user, model);
        List<Ticket> tickets = ticketService.findBySeance(seance);
        model.addAttribute("seance", seance);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("ticket/return/{id}")
    public String returnTicket(@PathVariable Long id){
        ticketService.returnTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("ticket/{ticket}")
    public String buy(@PathVariable(value = "ticket") Ticket ticket,
                      @AuthenticationPrincipal User user) {
        ticketService.sell(user, ticket);
        return "redirect:/tickets";
    }

    private void checkUser(@AuthenticationPrincipal User user, Model model) {
        boolean isAuthenticated = false;
        boolean isAdmin = false;
        if(user != null) {
            isAuthenticated = true;
            if (user.isAdmin())
                isAdmin = true;
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
    }
}
