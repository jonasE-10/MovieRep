import app.api.TMDbClient;
import app.config.HibernateConfig;
import app.dto.*;
import app.entities.Actor;
import app.entities.Director;
import app.entities.Genre;
import app.entities.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
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

        //lists to store movie, and keeping track of directors&actors
        List<Movie> allMovieEntities = new ArrayList<>();
        List<Actor> allActorEntities = new ArrayList<>();
        List<Director> allDirectorEntities = new ArrayList<>();
        List<Genre> allGenreEntities = new ArrayList<>();

        for (MovieDTO dto : movies) {
                //save id for later
                int tempMovieId = dto.id();

                //create movie from DTO
                Movie movie = new Movie();
                movie.setId(dto.id());
                movie.setTitle(dto.title());
                movie.setVote_avg(dto.vote_average());
                movie.setPopularity(dto.popularity());
                movie.setReleaseDate(LocalDate.parse((dto.release_date())));

                //use movieId to create normal endpoint, to get genres
                String jsonGenre = client.getMovie(tempMovieId);
                GenreListDTO genres = objectMapper.readValue(jsonGenre, GenreListDTO.class);

                for (GenreDTO gDTO : genres.genres()) {
                    Genre genre = null;

                    //check if genre already made
                    for (Genre alreadyMadeGenre : allGenreEntities) {
                        if (alreadyMadeGenre.getId() == gDTO.id()) {
                            genre = alreadyMadeGenre;
                            break;
                        }
                    }

                    //if not made, make em
                    if (genre == null) {
                        genre = new Genre();
                        genre.setId(gDTO.id());
                        genre.setName(gDTO.name());
                        allGenreEntities.add(genre);
                    }

                    //add to movie no matter if made before or not
                    movie.getGenres().add(genre);

                }


                //use movieId to create Credits
                String jsonCredit = client.getCreditsByMovieId(tempMovieId);
                CreditsDTO credits = objectMapper.readValue(jsonCredit, CreditsDTO.class);


                //actors
                for (ActorDTO aDTO : credits.cast()) {
                    Actor actor = null;

                    //check if actor is already made
                    for (Actor alreadyMadeActor : allActorEntities) {
                        if (alreadyMadeActor.getId() == aDTO.id()) {
                            actor = alreadyMadeActor;
                            break;
                        }
                    }

                    //if not made, make em
                    if (actor == null) {
                        actor = new Actor();
                        actor.setId(aDTO.id());
                        actor.setGender(aDTO.gender());
                        actor.setName(aDTO.name());
                        allActorEntities.add(actor);

                    }

                    // add to movie no matter if made before or not
                    movie.getActors().add(actor);

                }

                //Crew seperating those with job as director
                for (CrewDTO cDTO : credits.crew()) {
                    if (cDTO.job().equals("Director")) {

                        //same principle as previus
                        Director director = null;

                        for (Director alreadyMadeDirector : allDirectorEntities) {
                            if (alreadyMadeDirector.getId() == cDTO.id()) {
                                director = alreadyMadeDirector;
                                break;
                            }
                        }

                        if (director == null) {
                            director = new Director();
                            director.setId(cDTO.id());
                            director.setGender(cDTO.gender());
                            director.setName(cDTO.name());
                            allDirectorEntities.add(director);
                        }

                        movie.setDirector(director);
                    }
                }

                allMovieEntities.add(movie);

        }

        //DB stuff
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (Movie movie : allMovieEntities) {
            em.persist(movie);
        }
        em.getTransaction().commit();
        em.close();

    }
}
