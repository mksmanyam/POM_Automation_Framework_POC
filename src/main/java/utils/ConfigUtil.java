package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    private static Properties properties;

    static {
        try {
            // Load the properties file from the classpath
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\utils\\configUtil.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found!");
        }
    }

    // Method to get the value for a given property key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
