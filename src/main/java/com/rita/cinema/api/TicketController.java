package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("ticket")
    public List<Ticket> all(@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        return ticketService.findByUser(user);
    }

    @PostMapping("ticket")
    public Ticket buy(@RequestParam(value = "line") int line,
                      @RequestParam(value = "place") int place,
                      @RequestParam(value = "seance") Seance seance,
                      @AuthenticationPrincipal User user,
                      Map<String, Object> model) {
        return ticketService.sell(user, seance, line, place);
    }
}
