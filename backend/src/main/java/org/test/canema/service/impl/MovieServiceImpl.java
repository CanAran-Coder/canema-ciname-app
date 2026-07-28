package org.test.canema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.canema.entity.Movies;
import org.test.canema.repository.MovieRepository;
import org.test.canema.service.MovieService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {


    public MovieRepository movieRepository;
    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movies> getMovies() {
        return movieRepository.findAll();
    }
    public List<Movies> getMoviesByDate(String dateStr) {

        LocalDate date = LocalDate.parse(dateStr);

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return movieRepository.findDistinctByShowTimeBetween(start, end);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteMovie(Long id) {
        Long response =  movieRepository.removeById(id);
        if (response == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        return ResponseEntity.ok().build();



    }

    @Override
    public ResponseEntity<String> addMovie(Movies movies) {

            var response = movieRepository.save(movies);
            if(response.getId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).build();

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
