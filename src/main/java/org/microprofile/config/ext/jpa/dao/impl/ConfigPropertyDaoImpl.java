package org.microprofile.config.ext.jpa.dao.impl;

import org.jboss.logging.Logger;
import org.microprofile.config.ext.jpa.dao.ConfigPropertyDao;
import org.microprofile.config.ext.jpa.model.ConfigProperty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ConfigPropertyDaoImpl implements ConfigPropertyDao {

    private final Logger logger = Logger.getLogger(ConfigPropertyDaoImpl.class);

    @Override
    public List<ConfigProperty> getConfigProperties(String application, String profile) {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("configsource-persistence-unit");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            TypedQuery<ConfigProperty> query = entityManager.createNamedQuery("getConfigPropertiesByApplicationAndProfile", ConfigProperty.class);
            query.setParameter("application", application);
            query.setParameter("profile", profile);
            List<ConfigProperty> result = query.getResultList();
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error while fetching config properties from database", ex);
        } finally {
            if (entityManager != null)
                entityManager.close();
            if (entityManagerFactory != null)
                entityManagerFactory.close();
        }
    }

}
