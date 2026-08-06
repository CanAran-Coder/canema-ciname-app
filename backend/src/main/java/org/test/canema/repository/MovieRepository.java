package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.dto.response.MovieWithShowtimeResponse;
import org.test.canema.entity.Movie;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> getMovieByTitle(String title);


    Optional<Movie> findByTitle(String title);
}
