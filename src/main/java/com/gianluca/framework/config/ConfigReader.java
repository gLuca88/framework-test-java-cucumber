package com.gianluca.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {


    private static final Properties properties = new Properties();


    static {
        loadProperties();
    }

    private static void loadProperties() {

        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("File config.properties non trovato");
            }
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento del file di configurazione", e);
        }

    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }


}
