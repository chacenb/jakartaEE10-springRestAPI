package com.demojpaapp.common;

import com.demojpaapp.service.HelloService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static com.demojpaapp.globals.Globals.PERSIST_UNIT_NAME;
import static com.demojpaapp.globals.Globals.PROPERTIES_FILE_NAME;

@ApplicationScoped
public class Resources {

    private static final Logger LOG = LogManager.getLogger(HelloService.class);
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void initResourcesClass() {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++");
        LOG.info("++++ RESOURCES CLASS INSTANTIATED +++++++");
        LOG.info("+++++++++++++++++++++++++++++++++++++++++");
//        entityManagerFactory = Persistence.createEntityManagerFactory(PERSIST_UNIT_NAME);
        entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
        LOG.info("+++++ E.M.FACTORY HAS VALUE {} ++++++++", entityManagerFactory);
    }

    @PreDestroy
    public void destroyingResourcesClass() {
        if (entityManagerFactory.isOpen()) entityManagerFactory.close();
        LOG.info("+++++ PreDestroying Resources class +++++++++");
    }

    /* first way to inject properties file */
    public Properties file() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputSream = classLoader.getResourceAsStream(PROPERTIES_FILE_NAME);
        Properties props = new Properties();
        try {
            props.load(inputSream);
            return props;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /* inject properties file using producer method */
    @Produces
    @CustomProperty
    @Dependent
    public Properties loadPropertiesFile() {
        LOG.info("++++ CREATING PROPERTIES FILE BEAN +++++++");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputSream = classLoader.getResourceAsStream(PROPERTIES_FILE_NAME);
        Properties props = new Properties();
        try {
            props.load(inputSream);
            LOG.info("++++ PROPERTIES FILE BEAN CREATED+++++++");
            return props;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        LOG.info("++++ ENTITY MANAGER BEAN CREATED+++++++");
        return entityManagerFactory.createEntityManager();
    }

    public void freeUpResource(@Disposes @Default EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
            LOG.info("++++ ENTITY MANAGER CLOSED +++++++");
        }
    }
}

