package org.test.canema.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // ✅ Spring Security paketi
import org.springframework.web.bind.annotation.*;
import org.test.canema.dto.response.MovieWithShowtimeResponse;
import org.test.canema.entity.Movie;
import org.test.canema.exception.error.ResourceNotFoundException;
import org.test.canema.service.MovieService;


import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    public MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PreAuthorize("permitAll")
    @GetMapping("/byDate/{date}")
    public List<MovieWithShowtimeResponse> getMoviesByDate(@PathVariable String date) {
        return movieService.getMoviesByDate(date);
    }
    


}
