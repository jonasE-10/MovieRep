package app.dao;

import app.entities.Director;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DirectorDAO {

    private EntityManager em;

    public DirectorDAO(EntityManager em) {
        this.em = em;
    }

    public List<Director> getAllDirectorsFromDB(){
        List<Director> allDirectors;
        allDirectors = em.createQuery("SELECT d FROM Director d", Director.class).getResultList();
        return allDirectors;
    }
}
