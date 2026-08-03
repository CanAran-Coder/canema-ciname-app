package org.test.canema.service;

import org.springframework.http.ResponseEntity;
import org.test.canema.dto.response.MovieWithShowtimeResponse;
import org.test.canema.entity.Movie;

import java.util.List;

public interface MovieService {
    public List<MovieWithShowtimeResponse> getMoviesByDate(String Date);

}
