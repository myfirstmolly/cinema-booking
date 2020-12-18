package com.rita.cinema.service;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Hall;
import com.rita.cinema.domain.HallType;
import com.rita.cinema.domain.Seance;

import java.util.Date;
import java.util.List;

public interface SeanceService {
    Seance add(Seance seance);
    List<Seance> findAll();
    Seance findById(Long id);
    List<Seance> findByDate(Date date);
    List<Seance> findByFilm(Film film);
    List<Seance> findByHallType(HallType hallType);
    List<Seance> findByDateAndHallType(Date date, HallType hallType);
    void deleteById(Long id);
    void deleteAllByFilm(Film film);
    void deleteAllByHall(Hall hall);
    Seance updatePrice(Long id, double newPrice);
    Seance updateDate(Long id, Date newDate);
}
