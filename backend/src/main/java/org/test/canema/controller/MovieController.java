package org.test.canema.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // ✅ Spring Security paketi
import org.springframework.web.bind.annotation.*;
import org.test.canema.entity.Movie;
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
    @GetMapping("/getMovies")
    public List<Movie> getMovies() {
        return  movieService.getMovies();
    }
    @PreAuthorize("permitAll")
    @GetMapping("/byDate/{date}")
    public List<Movie> getMoviesByDate(@PathVariable String date) {
        return movieService.getMoviesByDate(date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id){
        return movieService.deleteMovie(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }
}
