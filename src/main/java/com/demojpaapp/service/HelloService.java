package com.demojpaapp.service;

import com.demojpaapp.common.CustomProperty;
//import com.demojpaapp.common.CustomProperties;
//import com.demojpaapp.common.CustomProperty;
import com.demojpaapp.entity.Employee;
import com.demojpaapp.persistence.HelloRepository;
import com.demojpaapp.service.ifaces.IHelloService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;


@SessionScoped
public class HelloService implements IHelloService, Serializable {

    private static final Logger LOG = LogManager.getLogger(HelloService.class);

    private String CHACE_PROPERTY;
    private String CHACE_PROPERTY2;
    private String anotherProperty;

    @Inject
    private HelloRepository helloRepository;

//    WORKING 100% TOO
//    @Inject
//    private Resources properties;
//    @PostConstruct
//    public void readFromPropertiesFile() {
//        CHACE_PROPERTY = properties.file().getProperty("CHACE_PROPERTY");
//        CHACE_PROPERTY2 = properties.file().getProperty("CHACE_PROPERTY2");
//        LOG.info("INSIDE HELLO SERVICE read property is {}", properties);
//    }

    @Inject
    @CustomProperty
    private Properties propFile;

//    private final String CHACE_PROPERTY = readFromProducesMethod.getProperty("CHACE_PROPERTY");
//    private final String CHACE_PROPERTY2 = readFromProducesMethod.getProperty("CHACE_PROPERTY2");
//    private String anotherProperty;

    @PostConstruct
    public void readFromPropertiesFile() {
        System.out.println("+++++ INSIDE HELLO SERVICE read property is " + propFile);
        CHACE_PROPERTY = propFile.getProperty("CHACE_PROPERTY");
        CHACE_PROPERTY2 = propFile.getProperty("CHACE_PROPERTY2");
    }

    @Override
    public Employee createEmployee(Employee e) {
        LOG.info("HelloService > createEmployee ");
        return helloRepository.createEmployee(e);
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOG.info("HelloService > getAllEmployees ");
        return helloRepository.getAllEmployees();
    }

    @Override
    public Employee updateEmployee(Long empId, Employee e) {
        LOG.info("HelloService > updateEmployee ");
        return null;
    }

    @Override
    public Employee DeleteEmployee(Long empId) {
        LOG.info("HelloService > DeleteEmployee ");
        return null;
    }

    @Override
    public void readPropertiesUsingMicroProfile() {
        System.out.println("***************************************************");
        System.out.println("************ READING DB PROPERTIES VALUES **********");
        System.out.println("***************************************************");
        LOG.info("chaceNameFromPropertiesFile is {}", CHACE_PROPERTY);
        LOG.info("chaceNameFromPropertiesFile 2 is {}", CHACE_PROPERTY2);
        LOG.info("anotherProperty is {}", anotherProperty);
        System.out.println("***************************************************");
    }
}
