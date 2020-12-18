package com.rita.cinema.api;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;
import com.rita.cinema.domain.User;
import com.rita.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> all(@AuthenticationPrincipal User user, Map<String, Object> model) {
        return filmService.findAll();
    }

    @GetMapping("{id}")
    public Film byId(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model) {
        return filmService.findById(id);
    }

    @GetMapping("/find-by-name")
    public List<Film> byName(@RequestParam String name, @AuthenticationPrincipal User user,
                             Map<String, Object> model) {
        return filmService.findByName(name);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Film add(@RequestParam(value = "name") String name,
                    @RequestParam(value = "director") String director,
                    @RequestParam(value = "year") Integer year,
                    @RequestParam(value = "genre") String genre,
                    @RequestParam(value = "summary", required = false) String summary,
                    @RequestParam(value = "rating", required = false) Rating rating,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model) {
        Film film = new Film(name, director, year, genre, summary, rating);
        return filmService.add(film);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model){
        filmService.deleteById(id);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public void delete(@PathVariable Long id,
                       @RequestParam(value = "summary", required = false) String summary,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        filmService.updateSummary(id, summary);
    }
}
