package com.rita.cinema.api;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;
import com.rita.cinema.domain.User;
import com.rita.cinema.service.FilmService;
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
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("film")
    public List<Film> all(Map<String, Object> model) {
        return filmService.findAll();
    }

    @GetMapping("film/{id}")
    public Film byId(@PathVariable Long id, Map<String, Object> model) {
        return filmService.findById(id);
    }

    @GetMapping("film/find-by-name")
    public List<Film> byName(@RequestParam String name, Map<String, Object> model) {
        return filmService.findByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit/film")
    public Film add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "director") String director,
                    @RequestParam(value = "releaseDate") String releaseDate,
                    @RequestParam(value = "genre") String genre,
                    @RequestParam(value = "summary", required = false) String summary,
                    @RequestParam(value = "file", required = false) String file,
                    @RequestParam(value = "rating", required = false) Rating rating,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model
    ) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date parsed = formatter.parse(releaseDate);
        return filmService.add(name, director, parsed, genre, summary, file, rating);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("edit/film/{id}")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        filmService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit/film/{id}")
    public void delete(@PathVariable Long id,
                       @RequestParam(value = "summary", required = false) String summary,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        filmService.updateSummary(id, summary);
    }
}
