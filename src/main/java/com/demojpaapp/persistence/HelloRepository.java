package com.demojpaapp.persistence;

import com.demojpaapp.entity.Employee;
import com.demojpaapp.persistence.ifaces.IHelloRepository;
import com.demojpaapp.service.HelloService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;


@SessionScoped
public class HelloRepository implements IHelloRepository, Serializable {
    private static final Logger LOG = LogManager.getLogger(HelloService.class);


//    WORKING 100% :  but not efficient
//    private EntityManagerFactory entityManagerFactory;
//    private EntityManager em;
//
//    @PostConstruct
//    public void initHelloRepository() {
//        LOG.info("++++++++++++  initHelloRepository +++++++++++++");
//        entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
//        em = entityManagerFactory.createEntityManager();
//    }
//    @PreDestroy
//    public void closeEntityManagerFactory() {
//        LOG.info("----------------- @PreDestroy");
//        em.close();
//        entityManagerFactory.close();
//    }


    //    WORKING 100% :  but not efficient
    @SuppressWarnings("CdiUnproxyableBeanTypesInspection")
    @Inject
    private EntityManager em;
//
//    @PostConstruct
//    public void initHelloRepository() {
//        LOG.info("++++++++++++  initHelloRepository +++++++++++++");
//        entityManagerFactory = Persistence.createEntityManagerFactory("demojpaappdbpersistenceunit");
//        em = entityManagerFactory.createEntityManager();
//    }
//    @PreDestroy
//    public void closeEntityManagerFactory() {
//        LOG.info("----------------- @PreDestroy");
//        em.close();
//        entityManagerFactory.close();
//    }


    @Override
    public Employee createEmployee(Employee employee) {
        LOG.info("helloRepository > createEmployee ");

        boolean successful = false;
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            successful = true;
        } catch (Exception e) {
            LOG.error("createEmployee ERROR ", e);
            em.getTransaction().rollback();
        }
        return successful ? employee : null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOG.info("helloRepository > getAllEmployees ");
        TypedQuery<Employee> namedQuery = em.createNamedQuery("GETALLEMPLOYEES", Employee.class);
        LOG.info("namedQuery is {} ", namedQuery);
        List<Employee> res = namedQuery.getResultList();
        LOG.info("res is {} ", res);
        return res;
    }

    @Override
    public Employee findEmployee(Long empId) {
        Employee e;
        /* e = (Employee) em.createQuery("select E from Employee E where E.id = : empId")
                  .setParameter("empId", empId)
                  .getSingleResult();
        * */
        e = em.find(Employee.class, empId);
        return e;
    }

    @Override
    public Employee updateEmployee(Long empId, Employee e) {
        LOG.info("helloRepository > updateEmployee ");
        return null;
    }

    @Override
    public Employee DeleteEmployee(Long empId) {
        LOG.info("helloRepository > DeleteEmployee ");
        return null;
    }

}
