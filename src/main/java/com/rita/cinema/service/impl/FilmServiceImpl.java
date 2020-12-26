package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;
import com.rita.cinema.repository.FilmRepository;
import com.rita.cinema.service.FilmService;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SeanceService seanceService;

    @Override
    public Film add(String name, String director, Date releaseDate, String genre, String summary, Rating rating) {
        Film film = new Film(name, director, releaseDate, genre, summary, rating);
        return filmRepository.save(film);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<Film> findByName(String name) {
        return filmRepository.findAllByName(name);
    }

    @Override
    public void deleteById(Long id) {
        Film film = filmRepository.getOne(id);
        seanceService.deleteAllByFilm(film);
        filmRepository.deleteById(id);
    }

    @Override
    public Film updateSummary(Long id, String newSummary) {
        Film film = filmRepository.findById(id).get();
        film.setSummary(newSummary);
        return filmRepository.save(film);
    }
}
