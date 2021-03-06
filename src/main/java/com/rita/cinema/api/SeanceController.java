package com.rita.cinema.api;

import com.rita.cinema.domain.*;
import com.rita.cinema.service.HallService;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SeanceController {
    @Autowired
    private SeanceService seanceService;
    @Autowired
    private HallService hallService;

    @GetMapping("seances")
    public String all(@RequestParam(required = false) String startDate,
                      @RequestParam(required = false) String endDate,
                      @RequestParam(required = false) String hallType,
                      @AuthenticationPrincipal User user,
                      Model model) throws ParseException {
        checkUser(user, model);
        HallType type = null;
        if(hallType != null) {
            if (hallType.equals("any")) type = null;
            else type = HallType.valueOf(hallType);
        }
        List<Seance> seances = seanceService.findAll(startDate, endDate, type);
        model.addAttribute("seances", seances);
        return "seances";
    }

    @GetMapping("seance/film/{film}")
    public String findByFilm(@PathVariable Film film,
                                     @AuthenticationPrincipal User user,
                                     Model model) {
        checkUser(user, model);
        List<Seance> seances = seanceService.findByFilm(film);
        model.addAttribute("seances", seances);
        return "seances";
    }

    @GetMapping("add/seance/{film}")
    public String addPage(@PathVariable(value = "film") Film film,
                          Model model,
                          @AuthenticationPrincipal User user) {
        model.addAttribute("halls", hallService.findAll());
        model.addAttribute("film", film);
        checkUser(user, model);
        return "add-seance";
    }

    @PostMapping("add/seance/{film}")
    public String add(@PathVariable(value = "film") Film film,
                      @RequestParam(value = "price") double price,
                      @RequestParam(value = "date") String date,
                      @RequestParam(value = "is3d", required = false) boolean is3d,
                      @RequestParam(value = "hall") Hall hall,
                      Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        Date parsed = null;
        try {
            parsed = formatter.parse(date);
        } catch (ParseException e) {
            model.addAttribute("message", "Bad date");
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("isAdmin", true);
            model.addAttribute("halls", hallService.findAll());
            model.addAttribute("film", film);
            return "add-seance";
        }
        Seance seance = new Seance(price, parsed, is3d, film, hall);
        seanceService.add(seance);
        return "redirect:/seances";
    }

    @GetMapping("delete/seance/{id}")
    public String delete(@PathVariable Long id){
        seanceService.deleteById(id);
        return "redirect:/seances";
    }

    @PutMapping("edit/seance/{id}")
    public String updatePrice(@PathVariable Long id,
                            @RequestParam(required = false) double newPrice,
                            @RequestParam(required = false) Date newDate){
        seanceService.updatePrice(id, newPrice);
        seanceService.updateDate(id, newDate);
        return "redirect:/seances";
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
