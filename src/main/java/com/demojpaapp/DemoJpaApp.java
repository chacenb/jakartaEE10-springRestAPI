/* USEFUL LINKS :
JAKARTA PERSISTENCE 3.1 DOC : https://jakarta.ee/specifications/persistence/3.1/
* Jakarta Persistence API CRUD EXAMPLE 1: https://www.mastertheboss.com/java-ee/jakarta-ee/jakarta-persistence-3-1-new-features/
* Jakarta Persistence API CRUD EXAMPLE 2: https://pramodshehan.medium.com/java-jakarta-persistence-api-jpa-6e94d0291c50
* https://blog.jetbrains.com/idea/2021/02/creating-a-simple-jakarta-persistence-application/
* */
package com.demojpaapp;

import com.demojpaapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.springframework.context.annotation.Bean;

@ApplicationPath("/api")
public class DemoJpaApp extends Application {

    @Bean
    public void initializeSomeDataToDB() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Employee("Chace", "Boris"));
        entityManager.persist(new Employee("Thierry", "M."));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}