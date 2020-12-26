package com.rita.cinema.service;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Hall;
import com.rita.cinema.domain.HallType;
import com.rita.cinema.domain.Seance;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SeanceService {
    Seance add(Seance seance);

    Seance findById(Long id);

    List<Seance> findByFilm(Film film);

    void deleteById(Long id);

    void deleteAllByFilm(Film film);

    void deleteAllByHall(Hall hall);

    Seance updatePrice(Long id, double newPrice);

    Seance updateDate(Long id, Date newDate);

    List<Seance> findAll(String start, String end, HallType hallType) throws ParseException;
}
