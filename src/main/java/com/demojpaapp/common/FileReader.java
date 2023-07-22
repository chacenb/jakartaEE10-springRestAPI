package com.demojpaapp.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static com.demojpaapp.globals.Globals.PROPERTIES_FILE_NAME;

public class FileReader {
    public static Properties properties = getPropertiesFile();
    public static Properties getPropertiesFile() {
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
