package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("tickets")
    public String all(@AuthenticationPrincipal User user,
                            Model model) {
        checkUser(user, model);
        model.addAttribute("tickets", ticketService.findByUser(user));
        return "tickets";
    }

    @GetMapping("ticket/return/{id}")
    public String returnTicket(@PathVariable Long id){
        ticketService.deleteById(id);
        return "redirect:/tickets";
    }

    @PostMapping("ticket")
    public Ticket buy(@RequestParam(value = "line") int line,
                      @RequestParam(value = "place") int place,
                      @RequestParam(value = "seance") Seance seance,
                      @AuthenticationPrincipal User user) {
        return ticketService.sell(user, seance, line, place);
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
