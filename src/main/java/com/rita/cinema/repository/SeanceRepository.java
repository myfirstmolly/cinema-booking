package com.rita.cinema.repository;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Hall;
import com.rita.cinema.domain.HallType;
import com.rita.cinema.domain.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    void deleteAllByHall(Hall hall);
    void deleteAllByFilm(Film film);
    List<Seance> findAllByDate(Date date);
    List<Seance> findAllByDateBetween(Date start, Date end);
    List<Seance> findAllByFilm(Film film);
}
