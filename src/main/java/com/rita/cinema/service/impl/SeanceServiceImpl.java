package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Hall;
import com.rita.cinema.domain.HallType;
import com.rita.cinema.domain.Seance;
import com.rita.cinema.repository.SeanceRepository;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SeanceServiceImpl implements SeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    public Seance add(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public List<Seance> findAll(String start, String end, HallType hallType) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.ENGLISH);
        Date startDate;
        Date endDate;
        if(start == null && end == null && hallType == null)
            return seanceRepository.findAll();
        if(start == null || start.isBlank())
            startDate = calendar.getTime();
        else startDate = formatter.parse(start);
        if(end == null || end.isBlank()) {
            calendar.add(Calendar.DATE, 10);
            endDate = calendar.getTime();
        }
        else endDate = formatter.parse(end);
        if(hallType == null)
            return seanceRepository.findAllByDateBetween(startDate, endDate);
        List<Seance> seances = findByDateAndHallType(startDate, endDate, hallType);
        Collections.sort(seances);
        return seances;
    }

    @Override
    public Seance findById(Long id) {
        return seanceRepository.findById(id).get();
    }

    @Override
    public List<Seance> findByFilm(Film film) {
        List<Seance> seances = seanceRepository.findAllByFilm(film);
        Collections.sort(seances);
        return seances;
    }

    public List<Seance> findByDateAndHallType(Date start, Date end, HallType hallType) {
        List<Seance> seances = seanceRepository.findAllByDateBetween(start, end);
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
