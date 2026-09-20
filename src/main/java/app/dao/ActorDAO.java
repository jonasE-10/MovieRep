package app.dao;

import app.entities.Actor;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ActorDAO {

    private EntityManager em;

    public ActorDAO(EntityManager em) {
        this.em = em;
    }

    public List<Actor> getAllActorsFromDB(){
        List<Actor> allActors;
        allActors = em.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
        return allActors;
    }
}
