package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping("hall")
    public List<Hall> all(@AuthenticationPrincipal User user, Map<String, Object> model) {
        return hallService.findAll();
    }

    @GetMapping("hall/{id}")
    public Hall byId(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model) {
        return hallService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit/hall")
    public Hall add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "linesNum") int linesNum,
                    @RequestParam(value = "seatsNum") int seatsNum,
                    @RequestParam(value = "hallType") HallType hallType,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model) {
        Hall hall = new Hall(name, linesNum, seatsNum, hallType);
        return hallService.add(hall);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("edit/hall/id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model){
        hallService.deleteById(id);
    }
}
