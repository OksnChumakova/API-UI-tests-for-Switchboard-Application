package ui.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.LoginPage;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    public void initPage() {
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void setUpMethod() {
    }

    @BeforeClass
    public void setUpClass() {
        instantiateDriver();
        initPage();
    }


    @AfterMethod
    public void closeLoginPopUpIfOpened(){
        loginPage.clear();

    }
//    // Login Pop Up

    @Test
    public void checkIfLoginPopUpShowsUp() {
        loginPage.assertThatLoginPopUpIsDisplayed();
    }

    // Login Button

    @Test
    public void checkIfLoginButtonIsPresentOnLoginPopUp() {
        loginPage.assertThatLoginButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfLoginButtonContainsText() {
        loginPage.assertThatLoginButtonContainsText();
    }

    @Test
    public void checkIfLoginButtonIsEnabled() {
        loginPage.assertThatLoginButtonIsEnabled();
    }

    // Login Input

    @Test
    public void checkIfLoginInputFieldIsPresentOnLoginPopUp() {
        loginPage.assertThatLoginInputFieldIsPresentOnThePage();
    }

    @Test
    public void checkIfPasswordInputFieldIsPresentOnLoginPopUp() {
        loginPage.assertThatPasswordInputFieldIsPresentOnThePage();
    }

    @Test
    public void checkIfLoginIsSuccessfulAfterProvidingValidCredentials() {
        loginPage.enterValidValueIntoLoginInputField()
                .enterValidValueIntoPasswordInputField()
                .pressLoginButton()
                .assertThatLoginIsSuccessful();

    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingInvalidLogin() {
        loginPage
                .enterInvalidValueIntoLoginInputField()
                .enterValidValueIntoPasswordInputField()
                .pressLoginButton()
                .assertThatLoginIsFailed();
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingInvalidPassword() {
        loginPage
                .enterValidValueIntoLoginInputField()
                .enterInvalidValueIntoPasswordInputField()
                .pressLoginButton()
                .assertThatLoginIsFailed();
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingNoCredentials() {
        loginPage
                .pressLoginButton()
                .assertThatLoginIsFailed();
    }

    @Test
    public void checkIfLogoutWorks() {
        loginPage
                .enterValidValueIntoLoginInputField()
                .enterValidValueIntoPasswordInputField()
                .pressLoginButton()
                .logout()
                .assertThatLogoutIsSuccessful();
    }
}
