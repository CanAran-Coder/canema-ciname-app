package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.dto.response.MovieWithShowtimeResponse;
import org.test.canema.entity.Movie;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<MovieWithShowtimeResponse> findDistinctByShowTimeBetween(LocalDateTime start, LocalDateTime end);

    long removeById(Long id);
}
