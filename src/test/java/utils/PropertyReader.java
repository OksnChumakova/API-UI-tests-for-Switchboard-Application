package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static String getPropertyFromFile(String propertyName) {

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("src/test/resources/framework.properties")) {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("Cannot read property value for " + propertyName);
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    private static String getProperty(String propertyName) {
        String res;
        if (System.getenv(propertyName) != null) {
            res = System.getenv(propertyName);
        }
        else if ((System.getProperty(propertyName) != null)) {
            res = System.getProperty(propertyName);
        } else {
            res = getPropertyFromFile(propertyName);
        }

        return res;
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static Browser getBrowser() {
        return Browser.valueOf(getProperty("browser"));
    }


    public static String getLogin() {
        return getProperty("login");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getPathToWebDriver() {
        return getProperty("pathToWebDriver");
    }
}
