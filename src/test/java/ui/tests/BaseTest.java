package ui.tests;

import api.steps.BaseStep;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

import static ui.pages.BasePage.driver;
import static ui.pages.LoginPage.login;

public abstract class BaseTest {



    @BeforeSuite
    public void instantiateDriver() {
        driver = DriverFactory.getDriver(PropertyReader.getBrowser());
        driver.get(PropertyReader.getUrl());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @BeforeClass
    public void setUpClass() {
        if (driver == null) {
            instantiateDriver();
        }
        login();
        initPage();
    }

    @AfterClass
    public void cleanUpClass() {
        logout();
    }

    @BeforeMethod
    public void setUpMethod() {
        BaseStep baseStep = new BaseStep();
        baseStep.deleteAppIdOnAllPages();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    public void initPage() {
    }

    public void logout() {

    }
}
