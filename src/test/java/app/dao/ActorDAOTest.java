package app.dao;

import app.config.HibernateConfig;
import app.entities.Actor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorDAOTest {

    @Test
    void getAllActorsFromDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(em);

        for (Actor actor : actorDAO.getAllActorsFromDB()) {
            System.out.println("Actor "+actor.getId()+": "+actor.getName());
        }

        em.close();
        emf.close();
    }
}