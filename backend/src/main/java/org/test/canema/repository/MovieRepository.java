package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.test.canema.entity.Movies;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movies,Long> {

    List<Movies> findDistinctByShowTimeBetween(LocalDateTime start, LocalDateTime end);

    long removeById(Long id);
}
