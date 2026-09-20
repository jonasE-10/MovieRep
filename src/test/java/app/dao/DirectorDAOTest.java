package app.dao;

import app.config.HibernateConfig;
import app.entities.Director;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;

class DirectorDAOTest {

    @Test
    void getAllDirectorsFromDB() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        DirectorDAO directorDAO = new DirectorDAO(em);

        for (Director director : directorDAO.getAllDirectorsFromDB()) {
            System.out.println("Director "+director.getId()+": "+director.getName());
        }

        em.close();
        emf.close();
    }
}