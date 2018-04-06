package com.example.demo.service;

import com.example.demo.domain.entity.Show;

import java.util.List;

public interface ShowService {

    List<Show> getMoviesFromCinemaEvents(Long cinemaId);
}
