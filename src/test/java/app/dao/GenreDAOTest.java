package app.dao;

import app.config.HibernateConfig;
import app.entities.Genre;
import app.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreDAOTest {

    @Test
    void getAllGenresFromDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        GenreDAO genreDAO = new GenreDAO(em);
        for (Genre genre : genreDAO.getAllGenresFromDB()) {
            System.out.println("Genre "+genre.getId()+": "+genre.getName());
        }
        em.close();
        emf.close();
    }

    @Test
    void getMoviesByGenre() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        GenreDAO genreDAO = new GenreDAO(em);
        System.out.println("Test for Genre Drama:");
        for (Movie movie : genreDAO.getMoviesByGenre("Drama")) {
            System.out.println("Movie "+movie.getId()+": "+movie.getTitle());
        }
        em.close();
        emf.close();

    }
}