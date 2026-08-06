package org.test.canema.config.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final HallAndSeatSeeder hallAndSeatSeeder;
    private final MovieSeeder movieSeeder;
    private final ShowtimeSeeder showtimeSeeder;


    @Override
    public void run(String... args) throws Exception {
        movieSeeder.seed();
        hallAndSeatSeeder.seed();
        showtimeSeeder.seed();
    }
}
