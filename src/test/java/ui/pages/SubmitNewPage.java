package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.common_functional.IConfirmationNotification;
import ui.common_functional.IItemGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubmitNewPage extends BasePage implements IItemGenerator, IConfirmationNotification {

    @FindBy(xpath = "//*[@id='appId']")
    private WebElement appInputField;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addToApprovalRequestButton;

    @FindBy(xpath = "//div[@class='container']//button[@class='btn btn-danger']")
    private WebElement removeButton;


    @FindBy(xpath = "//button[text()='Submit All']")
    private WebElement requestApprovalButton;

    @FindBy(xpath = "//table//tbody/tr[1]")
    private WebElement addedAppId;

    @FindBy(xpath = "//div[@class='modal-dialog']//button[@class='btn btn-primary']")
    WebElement requestApprovalButtonOnRequestApprovalPopUp;

    @FindBy(xpath = "//div[@class='modal-title h4'][text()='Confirm Application Approval Request']")
    private WebElement requestApprovalPopUp;

    private String dateOfRequest;


    public SubmitNewPage() {
        super();
        pressSubmitNewHeaderItem();
    }

    public SubmitNewPage generateItems(int quantity) {
        for (int i = 1; i <= quantity; i++) {
            pasteValueToAppInputField(String.valueOf(i))
                    .pressAddToApprovalRequestButton();
        }
        return this;
    }


    // App Input Field


    public SubmitNewPage assertThatAppInputFieldIsPresentOnThePage() {
        assertThat(appInputField.isDisplayed())
                .as("App Input Field element was not found").isTrue();
        return this;
    }


    public SubmitNewPage pasteValueToAppInputField(String text) {
        wait(1);
        appInputField.sendKeys(text);
        return this;
    }

    // Add to Approval Request Button

    public SubmitNewPage pressAddToApprovalRequestButton() {
        addToApprovalRequestButton.click();
        wait(1);
        return this;
    }

    public SubmitNewPage assertThatAddToApprovalRequestButtonContainsText() {
        String text = "Add";
        assertThat(addToApprovalRequestButton.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public SubmitNewPage assertThatAddToApprovalRequestButtonIsEnabled() {

        assertThat(addToApprovalRequestButton.isEnabled())
                .as("Add To Approval Request Button is NOT enabled").isTrue();
        return this;
    }


    public SubmitNewPage assertThatAddToApprovalRequestButtonIsPresentOnThePage() {
        assertThat(addToApprovalRequestButton.isDisplayed())
                .as("Add To Approval Request Button element was not found").isTrue();
        return this;
    }

    // Request Approval Button

    public SubmitNewPage pressRequestApprovalButton() {

        requestApprovalButton.click();
        wait(2);
        dateOfRequest = getDateOfRequest();
        return this;
    }


    public SubmitNewPage assertThatRequestApprovalButtonIsPresentOnThePage() {
        assertThat(requestApprovalButton.isDisplayed())
                .as("RequestApprovalButton element was not found").isTrue();
        return this;
    }


    public SubmitNewPage assertThatRequestApprovalButtonContainsText() {
        String text = "Submit All";
        assertThat(requestApprovalButton.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }


    public SubmitNewPage assertThatRequestApprovalButtonIsEnabled() {
        assertThat(requestApprovalButton.isEnabled())
                .as("Request Approval Button is NOT enabled").isTrue();
        return this;
    }


    public SubmitNewPage assertThatAddedAppIdIsPresentOnThePage() {
        assertThat(isElementFound(By.xpath("//table//tbody/tr[1]"), 3))
                .as("Added App ID element was not found").isTrue();
        return this;

    }

    private boolean isAppIdIsFound(String appId) {
        List<WebElement> elements = driver.findElements(By.xpath("//tbody//td"));
        for (WebElement element : elements) {
            if (element.getText().equals(appId)) {
                return true;
            }
        }
        return false;
    }

    public SubmitNewPage asserThatAddedAppIdIsPresentInTheList(String appId) {
        assertThat(isAppIdIsFound(appId))
                .as("Added App ID element was not found").isTrue();
        return this;
    }


    public SubmitNewPage assertThatAddedAppIdIsDistinctOnThePage() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr"));
        assertThat(listOfElements.size())
                .as("App IDs in the table are NOT distinct").isEqualTo(1);
        return this;

    }

    public SubmitNewPage assertThatTwoAddedAppIdAreDisplayedOnThePage() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr"));
        assertThat(listOfElements.size())
                .as("App IDs in the table are NOT distinct").isEqualTo(2);
        return this;

    }


    public SubmitNewPage assertThatAddedAppIdContainsText(String text) {
        assertThat(addedAppId.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public SubmitNewPage assertThatTableWithAddedAppIdIsEmpty() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr"));
        assertThat(listOfElements.size())
                .as("Table of App IDs is NOT empty").isEqualTo(0);
        return this;
    }


    public String getDateOfRequest() {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HHz");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(currentTime);
    }

    // Request Approval Pop Up

    public SubmitNewPage assertThatRequestApprovalPopUpIsDisplayed() {
        assertThat(requestApprovalPopUp.isDisplayed())
                .as("Request Approval PopUp is not displayed").isTrue();
        return this;
    }

    public SubmitNewPage pressRequestApprovalButtonOnRequestApprovalPopUp() {
        requestApprovalButtonOnRequestApprovalPopUp.click();
        wait(3);
        return this;
    }


    public SubmitNewPage assertThatRequestApprovalButtonOnRequestApprovalPopUpContainsText() {
        String text = "Request Approval";
        assertThat(requestApprovalButtonOnRequestApprovalPopUp.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public SubmitNewPage assertThatRequestApprovalButtonIsPresentOnRequestApprovalPopUp() {
        assertThat(requestApprovalButtonOnRequestApprovalPopUp.isDisplayed())
                .as("Cancel button was not found on Request Approval PopUp").isTrue();
        return this;
    }


    public SubmitNewPage assertThatRequestApprovalButtonOnRequestApprovalPopUpIsEnabled() {
        assertThat(requestApprovalButtonOnRequestApprovalPopUp.isEnabled())
                .as("Request Approval Button on Request Approval Pop Up is NOT enabled").isTrue();
        return this;
    }


    public void setUrlForDriver() {
        pressSubmitNewHeaderItem();
    }

}
