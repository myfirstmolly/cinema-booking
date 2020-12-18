package com.rita.cinema.service;

import com.rita.cinema.domain.Hall;

import java.util.List;
import java.util.UUID;

public interface HallService {
    Hall add(Hall hall);
    List<Hall> findAll();
    Hall findById(Long id);
    void deleteById(Long id);
}
