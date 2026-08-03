package org.test.canema.config.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.canema.entity.Hall;
import org.test.canema.entity.Seat;
import org.test.canema.repository.HallRepository;
import org.test.canema.repository.SeatRepository;


@RequiredArgsConstructor
@Component
public class HallAndSeatSeeder {
    public final HallRepository hallRepository;
    public final SeatRepository seatRepository;
    public void seed(){
        Hall hall1 = new Hall();
        hall1.setName("Hall1");
        hall1.setTotalRows(5);
        hall1.setSeatsPerRow(15);

        Hall hall2 = new Hall();
        hall2.setName("Hall2");
        hall2.setTotalRows(8);
        hall2.setSeatsPerRow(15);

        Hall hall3 = new Hall();
        hall3.setName("Hall3");
        hall3.setTotalRows(10);
        hall3.setSeatsPerRow(15);

        hallRepository.save(hall1);
        hallRepository.save(hall2);
        hallRepository.save(hall3);

        setSeats(hall1);
        setSeats(hall2);
        setSeats(hall3);
    }


    public void setSeats(Hall hall){

        for(var i = 1; i<=hall.getSeatsPerRow()*hall.getTotalRows(); i++ ){
            Seat seat = new Seat();
            seat.setHall(hall);
            seat.setSeatNumber(i);
            seatRepository.save(seat);

        }

    }

}
