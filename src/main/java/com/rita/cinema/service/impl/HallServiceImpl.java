package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Hall;
import com.rita.cinema.repository.HallRepository;
import com.rita.cinema.service.HallService;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public Hall add(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public Hall findById(Long id) {
        return hallRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        hallRepository.deleteById(id);
    }
}
