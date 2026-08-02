package org.test.canema.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.canema.entity.Movie;
import org.test.canema.repository.MovieRepository;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner init(MovieRepository movieRepository) {

        return args -> {
            if (movieRepository.count() == 0) {
                Movie movie1 = new Movie();
                movie1.setTitle("Avengers Endgame");
                movie1.setDescription("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.");




                movie1.setDurationMinutes(181);
                movie1.setImageURL("https://images5.alphacoders.com/998/thumb-1920-998470.jpg");

                Movie movie2 = new Movie();
                movie2.setTitle("The Godfather");
                movie2.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");




                movie2.setDurationMinutes(175);

                movie2.setImageURL("https://images5.alphacoders.com/131/thumb-1920-1315822.jpg");

                movieRepository.save(movie1);
                movieRepository.save(movie2);
            }
        };
    }
}