package org.test.canema.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.canema.dto.request.ShowTimeRequest;
import org.test.canema.entity.Hall;
import org.test.canema.entity.Movie;
import org.test.canema.entity.Showtime;
import org.test.canema.repository.HallRepository;
import org.test.canema.repository.MovieRepository;
import org.test.canema.repository.ShowtimeRepository;
import org.test.canema.service.ShowTimeService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowTimeServiceImpl implements ShowTimeService {

    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    @Transactional
    public void addShowTime(ShowTimeRequest request) {

        Hall hall = hallRepository.findHallByName(request.hallName());
        if (hall == null) {
            throw new RuntimeException("Hall not found: " + request.hallName());
        }

        Movie movie = movieRepository.findByTitle(request.title())
                .orElseGet(() -> {
                    Movie newMovie = new Movie();
                    newMovie.setImageURL(request.imageURL());
                    newMovie.setDescription(request.description());
                    newMovie.setTitle(request.title());
                    newMovie.setDurationMinutes(request.durationMinutes());

                    return movieRepository.save(newMovie);
                });

        List<Showtime> showtimesToSave = new ArrayList<>();

        for (String item : request.showTime()) {
            LocalDateTime startTime = LocalDateTime.parse(item);

            Showtime showtime = new Showtime();
            showtime.setPrice(BigDecimal.valueOf(request.price()));
            showtime.setHall(hall);
            showtime.setMovie(movie);
            showtime.setStartTime(startTime);
            showtime.setEndTime(startTime.plusMinutes(request.durationMinutes()));

            showtimesToSave.add(showtime);
        }

        showtimeRepository.saveAll(showtimesToSave);
        log.info("Successfully added {} showtimes for movie: {}", showtimesToSave.size(), movie.getTitle());
    }
}