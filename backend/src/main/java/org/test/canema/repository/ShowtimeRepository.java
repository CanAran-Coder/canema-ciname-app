package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.canema.entity.Showtime;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
    @Query("SELECT s FROM Showtime s " +
            "JOIN FETCH s.movie m " +
            "JOIN FETCH s.hall h " +
            "WHERE s.startTime >= :start AND s.startTime <= :end " +
            "ORDER BY s.startTime ASC")
    List<Showtime> findAllByStartTimeBetweenWithMovieAndHall(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

}
