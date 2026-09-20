package app.dao;

import app.entities.Genre;
import app.entities.Movie;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreDAO {

    private EntityManager em;

    public GenreDAO(EntityManager em) {
        this.em = em;
    }

    public List<Genre> getAllGenresFromDB(){
        List<Genre> allGenres;
        allGenres = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
        return allGenres;
    }

    public List<Movie> getMoviesByGenre(String genreName) {

        //same stragegi as the getMovieByTitle
        genreName = genreName.toLowerCase();
        List<Movie> allMovies;

        //using JOIN to list movies in relationship with the chosen Genre name
        allMovies = em.createQuery("SELECT m FROM Movie m JOIN m.genres g WHERE LOWER(g.name) LIKE :genreName", Movie.class).setParameter("genreName", "%" + genreName + "%").getResultList();
        return allMovies;
    }
}
