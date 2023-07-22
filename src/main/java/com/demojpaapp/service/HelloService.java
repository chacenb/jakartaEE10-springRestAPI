package com.demojpaapp.service;

import com.demojpaapp.entity.Employee;
import com.demojpaapp.persistence.HelloRepository;
import com.demojpaapp.service.ifaces.IHelloService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

//import static com.demojpaapp.common.FileReader.getPropertiesFile;
import static com.demojpaapp.common.FileReader.properties;

@SessionScoped
public class HelloService implements IHelloService, Serializable {
    private static final Logger LOG = LogManager.getLogger(HelloService.class);

//    private  String CHACE_PROPERTY;
    private final String CHACE_PROPERTY = properties.getProperty("CHACE_PROPERTY");;
    private final String CHACE_PROPERTY2 = properties.getProperty("CHACE_PROPERTY2");;

//    @Inject
//    @ConfigProperty(name = "anotherProperty", defaultValue = "cannot read property")
    private String anotherProperty;

    @PostConstruct
    public void readFromPropertiesFile() {
//        CHACE_PROPERTY = properties.getProperty("CHACE_PROPERTY");
//        CHACE_PROPERTY2 = getPropertiesFile().getProperty("CHACE_PROPERTY2", "property is undefined");
    }

    @Inject
    private HelloRepository helloRepository;

    /* READING VALUES FROM PROPERTIES FILE AND INJECTING INSIDE PROPERTIES */
    /* this injects values from properties files present in the classpath */
/* NOT WORKING .... dependency is >> org.eclipse.microprofile.config:microprofile-config-api
    @Inject // inject is required to import the property
    @ConfigProperty(name="CHACE_PROPERTY")
    private String CHACE_PROPERTY;

    @Inject
    @ConfigProperty(name="anotherProperty")
    private String anotherProperty;
*/


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
