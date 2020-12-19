package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class SeanceController {
    @Autowired
    private SeanceService seanceService;

    @GetMapping("seance")
    public List<Seance> all(@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        return seanceService.findAll();
    }

    @GetMapping("seance/{id}")
    public Seance byId(@PathVariable Long id,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model) {
        return seanceService.findById(id);
    }

    @GetMapping("seance/date")
    public List<Seance> filterByDate(@RequestParam Date date,
                                     @AuthenticationPrincipal User user,
                                     Map<String, Object> model) {
        return seanceService.findByDate(date);
    }

    @GetMapping("seance/hall-type")
    public List<Seance> filterByHallType(@RequestParam HallType hallType,
                                         @AuthenticationPrincipal User user,
                                         Map<String, Object> model) {
        return seanceService.findByHallType(hallType);
    }

    @GetMapping("seance/film")
    public List<Seance> filterByFilm(@RequestParam Film film,
                                     @AuthenticationPrincipal User user,
                                     Map<String, Object> model) {
        return seanceService.findByFilm(film);
    }

    @GetMapping("seance/date&hall")
    public List<Seance> filterByDateAndHall(@RequestParam Date date,
                                     @RequestParam HallType hallType,
                                     @AuthenticationPrincipal User user,
                                     Map<String, Object> model) {
        return seanceService.findByDateAndHallType(date, hallType);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit/seance")
    public Seance add(@RequestParam(value = "price") double price,
                    @RequestParam(value = "date") String date,
                    @RequestParam(value = "is3d") boolean is3d,
                    @RequestParam(value = "film") Film film,
                    @RequestParam(value = "hall") Hall hall,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date parsed = formatter.parse(date);
        Seance seance = new Seance(price, parsed, is3d, film, hall);
        return seanceService.add(seance);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("edit/seance/{id}")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        seanceService.deleteById(id);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("edit/seance/{id}/price")
    public void updatePrice(@PathVariable Long id,
                       @RequestParam double newPrice,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        seanceService.updatePrice(id, newPrice);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("edit/seance/{id}/date")
    public void updateDate(@PathVariable Long id,
                       @RequestParam Date newDate,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model) {
        seanceService.updateDate(id, newDate);
    }
}
