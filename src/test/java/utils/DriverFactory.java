package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;


public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(Browser browser) {
        if (driver == null) {
            File file;
            switch (browser) {
                case CHROME:
                    file = new File(PropertyReader.getPathToWebDriver() + "chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
//                    driver = new ChromeDriver();
                    break;
            }
//            driver.manage().window().maximize();

            return driver;
        }
        return driver;
    }
}
