package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Hall;
import com.rita.cinema.domain.HallType;
import com.rita.cinema.domain.Seance;
import com.rita.cinema.repository.SeanceRepository;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    public Seance add(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    @Override
    public Seance findById(Long id) {
        return seanceRepository.findById(id).get();
    }

    @Override
    public List<Seance> findByDate(Date date) {
        return seanceRepository.findAllByDate(date);
    }

    @Override
    public List<Seance> findByFilm(Film film) {
        return seanceRepository.findAllByFilm(film);
    }

    @Override
    public List<Seance> findByHallType(HallType hallType) {
        List<Seance> matchingSeances = new ArrayList<>();
        List<Seance> seances = seanceRepository.findAll();
        for (Seance seance :
                seances) {
            if (seance.getHall().getHallType() == hallType)
                matchingSeances.add(seance);
        }
        return matchingSeances;
    }

    @Override
    public List<Seance> findByDateAndHallType(Date date, HallType hallType) {
        List<Seance> seances = seanceRepository.findAllByDate(date);
        List<Seance> matchingSeances = new ArrayList<>();
        for (Seance seance :
                seances) {
            if (seance.getHall().getHallType() == hallType)
                matchingSeances.add(seance);
        }
        return matchingSeances;
    }

    @Override
    public void deleteById(Long id) {
        seanceRepository.deleteById(id);
    }

    @Override
    public void deleteAllByFilm(Film film) {
        seanceRepository.deleteAllByFilm(film);
    }

    @Override
    public void deleteAllByHall(Hall hall) {
        seanceRepository.deleteAllByHall(hall);
    }

    @Override
    public Seance updatePrice(Long id, double newPrice) {
        Seance seance = seanceRepository.getOne(id);
        seance.setPrice(newPrice);
        return seanceRepository.save(seance);
    }

    @Override
    public Seance updateDate(Long id, Date newDate) {
        Seance seance = seanceRepository.getOne(id);
        seance.setDate(newDate);
        return seanceRepository.save(seance);
    }
}
