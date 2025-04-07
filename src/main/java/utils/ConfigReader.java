package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;
    
    public static void loadConfig() {
        prop = new Properties();
        try {
            // Load using class loader from the resources folder
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("config.properties file not found in resources folder");
            }
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (prop == null) {
            loadConfig();
        }
        return prop.getProperty(key);
    }
}
