package app.dao;

import app.entities.Movie;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovieDAO {

    private EntityManager em;

    public MovieDAO(EntityManager em) {
        this.em = em;
    }

    public List<Movie> getAllMoviesFromDB(){
        List<Movie> allMovies;
        allMovies = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        return allMovies;
    }

    public List<Movie> getMovieByTitle(String title) {

        //List so I can get multiple results, instead of having user needing to be extremely specific
        List<Movie> allMovies;

        //putting string to always be lower case
        title = title.toLowerCase();

        //LOWER converts Database title to lowercase; LIKE matches, :title placeholder set in .setParameter
        allMovies = em.createQuery("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE :title").setParameter("title", "%" + title + "%").getResultList();

        return allMovies;
    }

    public Movie findById(int id) {
        //can use EntityManager to find it, instead of using a query
        return em.find(Movie.class, id);
    }

    public void saveMovieInDB(Movie movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    public void updateMovieInDB(Movie movie) {
        em.getTransaction().begin();
        em.merge(movie);
        em.getTransaction().commit();
    }

    public void deleteMovieInDB(int id){
        em.getTransaction().begin();
        Movie movieToDelete = findById(id);

        if (movieToDelete != null) {
            em.remove(movieToDelete);
        }
        em.getTransaction().commit();
    }

    public double getAvgRatingAllMoviesInDB(){
        double avgRating;
        avgRating = em.createQuery("SELECT AVG(m.vote_avg) FROM Movie m", double.class).getSingleResult();
        return avgRating;
    }

    public List<Movie> getTop10RatedMoviesInDB(){
        List<Movie> top10Movies;
        top10Movies = em.createQuery("SELECT m FROM Movie m ORDER BY m.vote_avg DESC", Movie.class).setMaxResults(10).getResultList();
        return top10Movies;
    }

    public List<Movie> getLowest10RatedMoviesInDB(){
        List<Movie> lowest10Movies;
        lowest10Movies = em.createQuery("SELECT m FROM Movie m ORDER BY m.vote_avg ASC", Movie.class).setMaxResults(10).getResultList();
        return lowest10Movies;
    }

    public List<Movie> getTop10PopularityMoviesInDB(){
        List<Movie> top10Movies;
        top10Movies = em.createQuery("SELECT m FROM Movie m ORDER BY m.popularity DESC", Movie.class).setMaxResults(10).getResultList();
        return top10Movies;
    }

}
