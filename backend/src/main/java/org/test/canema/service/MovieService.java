package org.test.canema.service;

import org.springframework.http.ResponseEntity;
import org.test.canema.entity.Movies;

import java.util.List;

public interface MovieService {
    public List<Movies> getMovies();
    public List<Movies> getMoviesByDate(String Date);
    public ResponseEntity<String> deleteMovie(Long id);
}
