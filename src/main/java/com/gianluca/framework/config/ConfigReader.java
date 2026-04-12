package com.gianluca.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {


    private static final Properties properties = new Properties();


    static {
        loadProperties();
    }

    private static void loadProperties() {


        String env = System.getProperty("env");

        if (env == null || env.isEmpty()) {
            env = "dev";
        }

        String fileName = "config-" + env + ".properties";

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException("File " + fileName + " non trovato");
            }

            properties.load(input);

            System.out.println("Configurazione caricata: " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento del file di configurazione", e);
        }
    }


    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Chiave '" + key + "' non trovata nel file di configurazione");
        }

        return value;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }


}
