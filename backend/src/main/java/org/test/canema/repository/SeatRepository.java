package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.entity.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findAllByHallIdAndSeatNumberIn(Long hallId, List<String> seatNumbers);
}
