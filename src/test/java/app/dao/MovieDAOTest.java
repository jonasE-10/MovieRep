package app.dao;

import app.config.HibernateConfig;
import app.entities.Actor;
import app.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class MovieDAOTest {

    @Test
    void getAllMoviesFromDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);
        for (Movie movie : movieDAO.getAllMoviesFromDB()) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle());
        }
        em.close();
        emf.close();
    }

    @Test
    void getMovieByTitle(){
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);
        String title = "Test";
        for (Movie movie : movieDAO.getMovieByTitle(title)) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle());
        }
        em.close();
        emf.close();
    }

    @Test
    void findMovieById() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);
        Movie movie = movieDAO.findById(1631807);
        System.out.println("Movie "+movie.getId()+": "+movie.getTitle());
        System.out.println("Movie is made by Director "+movie.getDirector().getId()+": "+movie.getDirector().getName());
        System.out.println("Actors in this movie: ");
        for (Actor actor : movie.getActors()) {
            System.out.println("Actor "+actor.getId()+": "+actor.getName());
        }
        em.close();
        emf.close();
    }

    @Test
    void saveMovieInDB(){
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        Movie movie = new Movie();
        movie.setId(123456789);
        movie.setTitle("Test Movie");
        movie.setPopularity(30);
        movie.setVote_avg(5);
        movie.setReleaseDate(LocalDate.of(2021, 9, 15));

        movieDAO.saveMovieInDB(movie);

        em.close();
        emf.close();
    }

    @Test
    void updateMovieInDB(){
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        Movie movie = new Movie();

        //Its based on ID, so that needs to stay same to update
        movie.setId(123456789);

        //can update these
        movie.setTitle("Updated Test Movie");
        movie.setPopularity(30);
        movie.setVote_avg(5);
        movie.setReleaseDate(LocalDate.of(2021, 9, 15));

        movieDAO.updateMovieInDB(movie);

        em.close();
        emf.close();
    }

    @Test
    void deleteMovieInDB(){
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        movieDAO.deleteMovieInDB(123456789);

        em.close();
        emf.close();
    }


    @Test
    void getAvgRatingAllMoviesInDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        double avgRating = movieDAO.getAvgRatingAllMoviesInDB();
        System.out.println("The average rating for all movies in database is: "+avgRating);

        em.close();
        emf.close();
    }

    @Test
    void getTop10RatedMoviesInDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        System.out.println("This is top 10 movies in Database based on voteRates:");
        for (Movie movie : movieDAO.getTop10RatedMoviesInDB()) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle()+" (with a rating of "+movie.getVote_avg()+")");
        }

        em.close();
        emf.close();
    }

    @Test
    void getLowest10RatedMoviesInDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        System.out.println("This is bottom 10 movies in Database based on voteRates:");
        for (Movie movie : movieDAO.getLowest10RatedMoviesInDB()) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle()+" (with a rating of "+movie.getVote_avg()+")");
        }

        em.close();
        emf.close();
    }


    @Test
    void getTop10PopularityMoviesInDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);

        System.out.println("This is top 10 movies in Database based on popularity:");
        for (Movie movie : movieDAO.getTop10PopularityMoviesInDB()) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle()+" (with a popularity of "+movie.getPopularity()+")");
        }

        em.close();
        emf.close();
    }
}