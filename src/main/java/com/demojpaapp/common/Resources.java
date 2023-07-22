package com.demojpaapp.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import jakarta.ws.rs.Produces;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import static com.demojpaapp.globals.Globals.PERSIST_UNIT_NAME;
import static com.demojpaapp.globals.Globals.PROPERTIES_FILE_NAME;

@ApplicationScoped
public class Resources {

    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void initResourcesClass() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++ Resources class instantiated +++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");

        System.out.println("+++++ init EntityManagerFactory +++++++++");
        entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
//        entityManagerFactory = Persistence.createEntityManagerFactory(PERSIST_UNIT_NAME);

    }

    @PreDestroy
    public void destroyingResourcesClass() {
        entityManagerFactory.close();
        System.out.println("+++++ PreDestroying Resources class +++++++++");
    }

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


    @Produces
    @CustomProperty
    @Dependent
    public Properties loadPropertiesFile() {
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


    @Produces
    @Default
//    @SessionScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes @Default EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
        if (entityManagerFactory.isOpen()) entityManagerFactory.close();
    }
}

