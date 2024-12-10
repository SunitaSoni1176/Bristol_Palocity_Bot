package PaylocityAutomation;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {



	private static Properties properties;

    // Static block to load the properties file
    static {
        try (FileInputStream fis = new FileInputStream("path_to_config.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load configuration file: " + e.getMessage());
        }
    }

    // Method to get a property value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }



}
