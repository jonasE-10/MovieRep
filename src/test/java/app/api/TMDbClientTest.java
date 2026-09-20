package app.api;

import app.dto.MovieDTO;
import app.dto.MovieListDTO;
import app.entities.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TMDbClientTest {

    @Test
    void getAllMoviesFromPageOne() throws Exception {
        TMDbClient client = new TMDbClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String json = client.getAllMoviesFromPage(1);
        MovieListDTO movies = objectMapper.readValue(json, MovieListDTO.class);
        System.out.println(movies.total_pages());
    }

    @Test
    void getAllMoviesFromAllPages() throws Exception {
        TMDbClient client = new TMDbClient();
        ObjectMapper objectMapper = new ObjectMapper();
        List<MovieDTO> movies = new ArrayList<>();
        int totalPages = 1;
        for (int i = 1; i <= totalPages ; i++) {
            String json = client.getAllMoviesFromPage(i);
            MovieListDTO moviesByPage = objectMapper.readValue(json, MovieListDTO.class);

            totalPages = moviesByPage.total_pages();
            movies.addAll(moviesByPage.results());
        }

        assertEquals(58, totalPages);
        for (MovieDTO movie : movies) {
            assertTrue(movie.id() > 0);
        }
        System.out.println(movies);
    }

    @Test
    void getMovie() throws Exception{
        TMDbClient client = new TMDbClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = client.getMovie(1631807);
        MovieDTO movie = objectMapper.readValue(json, MovieDTO.class);

        assertEquals("The Secret Woman", movie.title());
    }
}