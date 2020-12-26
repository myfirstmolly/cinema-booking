package com.rita.cinema.service;

import com.rita.cinema.domain.Hall;

import java.util.List;

public interface HallService {
    Hall add(Hall hall);

    List<Hall> findAll();

    Hall findById(Long id);

    void deleteById(Long id);
}
