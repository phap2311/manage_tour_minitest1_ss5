package com.example.manager_tour.service;

import com.example.manager_tour.model.Tour;

import java.util.List;

public interface IHibernateTourService {
    List<Tour> findAll();

    void save(Tour tour);

    Object findById(int id);

    void remove(int id);

    void edit(Tour tour);
}
