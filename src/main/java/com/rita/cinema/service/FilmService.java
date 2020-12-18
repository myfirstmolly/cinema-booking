package com.rita.cinema.service;

import com.rita.cinema.domain.Film;

import java.util.List;

public interface FilmService {
    Film add(Film film);
    List<Film> findAll();
    Film findById(Long id);
    List<Film> findByName(String name);
    void deleteById(Long id);
    Film updateSummary(Long id, String newSummary);
}
