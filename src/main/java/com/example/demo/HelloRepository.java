package com.example.demo;

//import com.demojpaapp.persistence.ifaces.IHelloRepository;
//import com.demojpaapp.service.HelloService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class HelloRepository implements Serializable {
//    private static final Logger LOG = LogManager.getLogger(HelloService.class);
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager em = entityManagerFactory.createEntityManager();

//    @Inject
//    EntityManager injectedEntityManager0;
//
//    @PersistenceContext
//    private EntityManager injectedEntityManager1;
//
//    @PersistenceContext(name = PERSISTENCE_UNIT)
//    private EntityManager injectedEntityManager2;

    /* Spring calls the methods annotated with @PostConstruct only once, just after the initialization of bean*/
//    @PostConstruct
//    public void initHelloRepository() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
//        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

//        em = entityManagerFactory.createEntityManager();
//
//        LOG.info("entityManager {}", em);
////        LOG.info("injectedEntityManager0 {}", injectedEntityManager0);
////        LOG.info("injectedEntityManager1 {}", injectedEntityManager1);
////        LOG.info("injectedEntityManager2 {}", injectedEntityManager2);
//    }

    /* A method annotated with @PreDestroy runs only once, just before Spring removes our bean from the application context.*/
//    @PreDestroy
//    public void closeEntityManagerFactory() {
//        entityManagerFactory.close();
//    }

//    @Override
    public Employee createEmployee(Employee employee) {
//        LOG.info("helloRepository > createEmployee ");

        boolean successful = false;
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            em.close();
            entityManagerFactory.close();
            successful = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return successful ? employee : null;
    }

//    @Override
    public List<Employee> getAllEmployees() {
//        LOG.info("helloRepository > getAllEmployees ");

        return em.createQuery("select E from Employee E", Employee.class).getResultList();
    }

//    @Override
    public Employee findEmployee(Long empId) {
        Employee e;
        /* e = (Employee) em.createQuery("select E from Employee E where E.id = : empId")
                  .setParameter("empId", empId)
                  .getSingleResult();
        * */
        e = em.find(Employee.class, empId);
        return e;
    }

//    @Override
    public Employee updateEmployee(Long empId, Employee e) {
//        LOG.info("helloRepository > updateEmployee ");
        return null;
    }

//    @Override
    public Employee DeleteEmployee(Long empId) {
//        LOG.info("helloRepository > DeleteEmployee ");
        return null;
    }

}
