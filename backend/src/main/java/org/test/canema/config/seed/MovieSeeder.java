package org.test.canema.config.seed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.canema.entity.Movie;
import org.test.canema.repository.MovieRepository;

@RequiredArgsConstructor
@Component
public class MovieSeeder {
    private final MovieRepository movieRepository;


    public void seed() {
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

        Movie movie3 = new Movie();
        movie3.setTitle("Interstellar");
        movie3.setDescription("In a dystopian future where Earth has become near-uninhabitable, a team of astronauts embark on a mission to find a new home for humanity.");
        movie3.setDurationMinutes(169);
        movie3.setImageURL("https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_.jpg");

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
    }

}
