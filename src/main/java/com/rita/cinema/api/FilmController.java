package com.rita.cinema.api;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;
import com.rita.cinema.domain.User;
import com.rita.cinema.service.FilmService;
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
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("films")
    public String all(Model model, @AuthenticationPrincipal User user) {
        boolean isAuthenticated = false;
        boolean isAdmin = false;
        if(user != null) {
            isAuthenticated = true;
            if (user.isAdmin())
                isAdmin = true;
        }
        List<Film> films = filmService.findAll();
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("films", films);
        return "films";
    }

    @GetMapping("film/{id}")
    public String byId(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        boolean isAuthenticated = false;
        boolean isAdmin = false;
        if(user != null) {
            isAuthenticated = true;
            if (user.isAdmin())
                isAdmin = true;
        }
        Film film = filmService.findById(id);
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("film", film);
        return "film-info";
    }

    @GetMapping("film/find-by-name")
    public List<Film> byName(@RequestParam String name, Model model, @AuthenticationPrincipal User user) {
        List<Film> films = filmService.findByName(name);
        model.addAttribute("films", films);
        return filmService.findByName(name);
    }

    @GetMapping("add/film")
    public String addPage(Model model) {
        model.addAttribute("isAuthenticated", true);
        model.addAttribute("isAdmin", true);
        return "add-film";
    }

    @PostMapping("add/film")
    public String add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "director") String director,
                    @RequestParam(value = "releaseDate") String releaseDate,
                    @RequestParam(value = "genre") String genre,
                    @RequestParam(value = "summary", required = false) String summary,
                    @RequestParam(value = "rating", required = false) Rating rating
    ) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = formatter.parse(releaseDate);
        filmService.add(name, director, parsed, genre, summary, rating);
        return "redirect:/films";
    }

    @GetMapping("delete/film/{id}")
    public String delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user){
        filmService.deleteById(id);
        return "redirect:/films";
    }

    @GetMapping("edit/film/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(value = "summary", required = false) String summary,
                       @AuthenticationPrincipal User user,
                       Model model){
        filmService.updateSummary(id, summary);
        return "redirect:/films";
    }
}
