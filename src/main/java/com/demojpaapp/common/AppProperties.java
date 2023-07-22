package com.demojpaapp.common;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Properties;
import static com.demojpaapp.globals.Globals.PROPERTIES_FILE_NAME;

@ApplicationScoped
public class AppProperties {
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
}
