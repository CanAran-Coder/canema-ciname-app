package org.test.canema.service;

import org.springframework.http.ResponseEntity;
import org.test.canema.entity.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> getMovies();
    public List<Movie> getMoviesByDate(String Date);
    public ResponseEntity<String> deleteMovie(Long id);
    public ResponseEntity<String> addMovie(Movie movie);
}
