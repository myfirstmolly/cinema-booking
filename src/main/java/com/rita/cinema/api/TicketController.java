package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> all(@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        return ticketService.findByUser(user);
    }

    @PostMapping
    public Ticket buy(@RequestParam(value = "name") int line,
                    @RequestParam(value = "director") int place,
                    @RequestParam(value = "year") Seance seance,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model) {
        return ticketService.sell(user, seance, line, place);
    }
}
