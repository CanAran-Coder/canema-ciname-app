package org.test.canema.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.canema.dto.response.MovieWithShowtimeResponse;
import org.test.canema.dto.response.ShowtimeResponse;
import org.test.canema.entity.Movie;
import org.test.canema.entity.Showtime;
import org.test.canema.repository.ShowtimeRepository;
import org.test.canema.service.MovieService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final ShowtimeRepository showtimeRepository;

   public MovieServiceImpl(ShowtimeRepository showtimeRepository){
       this.showtimeRepository = showtimeRepository;
   }




    @Cacheable(value = "moviesByDate",key = "#dateStr")
    public List<MovieWithShowtimeResponse> getMoviesByDate(String dateStr) {

        LocalDate date = LocalDate.parse(dateStr);

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        List<Showtime> ShowtimeData = showtimeRepository.findAllByStartTimeBetweenWithMovieAndHall(start, end);
        Map<Movie,List<Showtime>> showTimesByMovie = ShowtimeData.stream().collect(Collectors.groupingBy(Showtime::getMovie));
        List<MovieWithShowtimeResponse> responseList = new ArrayList<>();

        for (Map.Entry<Movie, List<Showtime>> entry : showTimesByMovie.entrySet()) {
            Movie movie = entry.getKey();
            List<Showtime> movieShowtimes = entry.getValue();


            List<ShowtimeResponse> showtimeDTOs = movieShowtimes.stream()
                    .map(st -> new ShowtimeResponse(st.getId(), st.getStartTime(), st.getPrice(), st.getHall().getName()))
                    .toList();


            MovieWithShowtimeResponse response = new MovieWithShowtimeResponse(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getDurationMinutes(),
                    movie.getImageURL(),
                    showtimeDTOs
            );

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"moviesByDate","allMovies"},allEntries = true)
    public ResponseEntity<String> deleteMovie(Long id) {
        Long response =  movieRepository.removeById(id);
        if (response == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        return ResponseEntity.ok().build();



    }

    @Override
    @CacheEvict(value = {"moviesByDate","allMovies"},allEntries = true)
    public ResponseEntity<String> addMovie(Movie movie) {

            var response = movieRepository.save(movie);
            if(response.getId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).build();

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
