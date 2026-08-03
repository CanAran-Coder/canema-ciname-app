package org.test.canema.config.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.canema.entity.Hall;
import org.test.canema.entity.Movie;
import org.test.canema.entity.Showtime;
import org.test.canema.repository.HallRepository;
import org.test.canema.repository.MovieRepository;
import org.test.canema.repository.ShowtimeRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowtimeSeeder {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    public void seed(){
        if(showtimeRepository.count()==0){
            List<Movie> movies = movieRepository.findAll();
            List<Hall> halls = hallRepository.findAll();
            if(movies.isEmpty() || halls.isEmpty()){
                return;
            }
            Movie movie1 = movies.get(0);
            Hall hall1 = halls.get(0);

            Movie movie2 = movies.get(1);
            Hall hall2 = halls.get(1);

            Movie movie3 = movies.get(2);
            Hall hall3 = halls.get(2);

            LocalDateTime today = LocalDateTime.now().withSecond(0).withNano(0);

            Showtime showtime1 = new Showtime();
            showtime1.setHall(hall1);
            showtime1.setMovie(movie1);
            showtime1.setPrice(new BigDecimal(12));
            showtime1.setStartTime(today.withHour(14).withMinute(0));
            showtime1.setEndTime(showtime1.getStartTime().plusMinutes(movie1.getDurationMinutes()));

            Showtime showtime2 = new Showtime();
            showtime2.setHall(hall2);
            showtime2.setMovie(movie2);
            showtime2.setPrice(new BigDecimal(17));
            showtime2.setStartTime(today.withHour(18).withMinute(0));
            showtime2.setEndTime(showtime2.getStartTime().plusMinutes(movie2.getDurationMinutes()));


            Showtime showtime3 = new Showtime();
            showtime3.setHall(hall3);
            showtime3.setMovie(movie3);
            showtime3.setPrice(new BigDecimal(15));
            showtime3.setStartTime(today.withHour(18).withMinute(0).plusDays(1));
            showtime3.setEndTime(showtime3.getStartTime().plusMinutes(movie3.getDurationMinutes()));

            showtimeRepository.save(showtime1);
            showtimeRepository.save(showtime2);
            showtimeRepository.save(showtime3);

        }
    }
}
