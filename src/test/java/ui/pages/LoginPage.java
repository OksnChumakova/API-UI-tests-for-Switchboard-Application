package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertyReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//div[@class='form-group']//input[@type='text']")
    private WebElement loginInputField;

    @FindBy(xpath = "//div[@class='form-group']//input[@type='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//div[@class='modal-footer']/button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement loginPopUp;

    @FindBy(xpath = "//div[@role='alert'][text()='Login Fail']")
    private WebElement loginFailureAlert;

    @FindBy(xpath = "//div[text()='Submit New Applications']")
    private WebElement titleThatIsDisplayedInCaseOfSuccessfulLogin;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/logout']")
    WebElement logoutHeaderItem;


    public LoginPage enterValidValueIntoLoginInputField() {
        loginInputField.sendKeys(PropertyReader.getLogin());
        return this;
    }

    public LoginPage enterInvalidValueIntoLoginInputField() {
        loginInputField.sendKeys("invalidValue");
        return this;
    }

    public LoginPage enterValidValueIntoPasswordInputField() {
        passwordInputField.sendKeys(PropertyReader.getPassword());
        return this;
    }

    public LoginPage enterInvalidValueIntoPasswordInputField() {
        passwordInputField.sendKeys("invalidValue");
        return this;
    }

    public LoginPage pressLoginButton() {
        loginButton.click();
        wait(2);
        return this;
    }

    public LoginPage assertThatLoginIsSuccessful() {
        assertThat(titleThatIsDisplayedInCaseOfSuccessfulLogin.isDisplayed())
                .as("Login Failure Alert is NOT displayed").isTrue();
        return this;
    }

    public LoginPage assertThatLoginIsFailed() {
        assertThat(loginFailureAlert.isDisplayed())
                .as("Login Failure Alert is NOT displayed").isTrue();
        return this;
    }

    // Login Button

    public LoginPage assertThatLoginButtonContainsText() {
        String text = "Login";
        wait(1);
        assertThat(loginButton.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }


    public LoginPage assertThatLoginButtonIsPresentOnThePage() {
        assertThat(loginButton.isDisplayed())
                .as("Login Button was not found").isTrue();
        return this;
    }

    public LoginPage assertThatLoginButtonIsEnabled() {
        assertThat(loginButton.isEnabled())
                .as("Login Button is NOT enabled").isTrue();
        return this;
    }

    // Login input field

    public LoginPage assertThatLoginInputFieldIsPresentOnThePage() {
        assertThat(loginInputField.isDisplayed())
                .as("Login Input Field was not found").isTrue();
        return this;
    }

    // Password input field

    public LoginPage assertThatPasswordInputFieldIsPresentOnThePage() {
        assertThat(passwordInputField.isDisplayed())
                .as("Password Input Field was not found").isTrue();
        return this;
    }

    public LoginPage assertThatLoginPopUpIsDisplayed() {
        assertThat(loginPopUp.isDisplayed())
                .as("Login Pop Up was not found").isTrue();
        return this;
    }

    public LoginPage cleanInputFields() {
        loginInputField.clear();
        passwordInputField.clear();

        return this;
    }

    public LoginPage logout() {
            logoutHeaderItem.click();
        return this;
    }

    public LoginPage assertThatLogoutIsSuccessful(){
        wait(1);
        return assertThatLoginPopUpIsDisplayed();
    }

    public void clear(){
        if(isElementFound(By.xpath("//div[@class='modal-content']"), 1)){
            cleanInputFields();
        } else{
           logout();
        }
        wait(1);
    }

    public static void login(){
        LoginPage loginPage = new LoginPage();
        loginPage.enterValidValueIntoLoginInputField()
                .enterValidValueIntoPasswordInputField()
                .pressLoginButton();

    }

}
