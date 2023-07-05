package com.example.demo;

//import com.demojpaapp.service.ifaces.IHelloService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
//import jakarta.inject.Inject;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.List;

@RequestScoped
public class HelloService  {
//    private static final Logger LOG = LogManager.getLogger(HelloService.class);

    @Inject
    private HelloRepository helloRepository;

//    @Override
    public Employee createEmployee(Employee e) {
//        LOG.info("HelloService > createEmployee ");

        return helloRepository.createEmployee(e);
    }

//    @Override
    public List<Employee> getAllEmployees() {
//        LOG.info("HelloService > getAllEmployees ");

        return helloRepository.getAllEmployees();
    }

//    @Override
    public Employee updateEmployee(Long empId, Employee e) {
//        LOG.info("HelloService > updateEmployee ");
        return null;
    }

//    @Override
    public Employee DeleteEmployee(Long empId) {
//        LOG.info("HelloService > DeleteEmployee ");
        return null;
    }
}
