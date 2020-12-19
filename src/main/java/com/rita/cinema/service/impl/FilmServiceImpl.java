package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Film;
import com.rita.cinema.domain.Rating;
import com.rita.cinema.repository.FilmRepository;
import com.rita.cinema.service.FilmService;
import com.rita.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SeanceService seanceService;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Film add(String name, String director, Date releaseDate, String genre, String summary, String fileName, Rating rating) {
        String fileNameEnc = UUID.randomUUID().toString() + "." + fileName;
        Film film = new Film(name, director, releaseDate, genre, summary, fileNameEnc, rating);
        return filmRepository.save(film);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<Film> findByName(String name) {
        return filmRepository.findAllByName(name);
    }

    @Override
    public void deleteById(Long id) {
        Film film = filmRepository.getOne(id);
        seanceService.deleteAllByFilm(film);
        filmRepository.deleteById(id);
    }

    @Override
    public Film updateSummary(Long id, String newSummary) {
        Film film = filmRepository.findById(id).get();
        film.setSummary(newSummary);
        return filmRepository.save(film);
    }

    private void saveFile(MultipartFile file, String fileName) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath + "/" + fileName));
        }
    }
}
