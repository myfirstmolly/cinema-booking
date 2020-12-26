package com.rita.cinema.service;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;

import java.util.Date;
import java.util.List;

public interface FilmService {
    Film add(String name, String director, Date releaseDate, String genre, String summary,
             Rating rating);

    List<Film> findAll();

    Film findById(Long id);

    List<Film> findByName(String name);

    void deleteById(Long id);

    Film updateSummary(Long id, String newSummary);
}
