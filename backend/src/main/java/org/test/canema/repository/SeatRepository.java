package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
}
