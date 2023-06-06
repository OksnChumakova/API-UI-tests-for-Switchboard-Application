package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Browser;
import utils.DriverFactory;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.APPLICATION_ID_1;
import static constants.Constants.APPLICATION_ID_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PendingApprovalPage extends BasePage {


    @FindBy(xpath = "//button[text()='Approve All']")
    private WebElement approveButton;

    @FindBy(xpath = "//div[@class='modal-title h4'][text()='Confirm Approve List']")
    WebElement requestApprovalPopUp;


    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[2]/div/div[1]/div/div/div/table/tbody/tr/td[2]")
    private WebElement dateOfFirstRequestForApprove;


    List<String> listOfAppIdInApprovalRequest;


    public List<String> getListOfAppIdInApprovalRequest() {
        return listOfAppIdInApprovalRequest;
    }

    public PendingApprovalPage() {
        super();
        pressPendingApprovalHeaderItem();
    }

// Approve Button

    public PendingApprovalPage pressApproveButton() {
        approveButton.click();
        wait(2);
        return this;
    }


    public void assertThatApproveButtonContainsText() {
        String text = "Approve All";
        assertThat(approveButton.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
    }


    public void assertThatApproveButtonIsPresentOnThePage() {
        assertThat(approveButton.isDisplayed())
                .as("Approve button element was not found").isTrue();
    }

    public void assertThatApproveButtonIsEnabled() {
        assertThat(approveButton.isEnabled())
                .as("Approve Button is NOT enabled").isTrue();
    }


    // Request UUID Button

    public PendingApprovalPage clickUUIDRequestButton() {
        driver.findElement(By.xpath("//table//a[1]")).click();
        return this;
    }

    //

    public PendingApprovalPage assertThatAddedRequestForApprovalIsDisplayedOnPendingApprovalPage(String date) {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/div[1]/div/div/div/table/tbody/tr"));
        assertThat(listOfElements.size())
                .as("Request for Approval is NOT displayed").isEqualTo(1);

        assertThat(dateOfFirstRequestForApprove.getText().replaceAll(":[0-9][0-9]:[0-9][0-9] ", ""))
                .as("The date of Request For Approval Is WRONG").isEqualTo(date);
        return this;

    }

    public PendingApprovalPage assertThatNoRequestsAreDisplayedOnPendingApprovalPage() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/div[1]/div/div/div/table/tbody/tr"));
        assertThat(listOfElements.size())
                .as("Request for Approval is displayed").isEqualTo(0);

        return this;

    }

    public PendingApprovalPage createApprovalRequest() {
        SubmitNewPage submitNewPage = new SubmitNewPage();

        listOfAppIdInApprovalRequest = new ArrayList<>();
        listOfAppIdInApprovalRequest.add(APPLICATION_ID_1);
        listOfAppIdInApprovalRequest.add(APPLICATION_ID_2);

        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_2)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressRequestApprovalButtonOnRequestApprovalPopUp();

        submitNewPage.pressPendingApprovalHeaderItem();

        return this;
    }

    public PendingApprovalPage createAndApproveAppId(){
        createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .pressApproveButtonOnRequestApprovalPopUp()
                .pressPublishedHeaderItem();

        return this;
    }



    public PendingApprovalPage assertThatAddedAppIdIsPresentInApprovalRequest() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//button[@class='btn btn-link']"));
        if (listOfElements.size() != 0) {
            for (WebElement webElement : listOfElements) {
                new WebDriverWait(DriverFactory.getDriver(Browser.CHROME), 5).until(ExpectedConditions.elementToBeClickable(webElement)).click();
                webElement.click();
                assertThatCorrectRequestIdIsDisplayedAfterClickingOnParticularRequest(webElement.getText());
                assertThatAppIdsInSingleRequestApprovalAreCorrect(listOfAppIdInApprovalRequest);
            }
        }
        return this;
    }


    public PendingApprovalPage assertThatAppIdsInSingleRequestApprovalAreCorrect(List<String> listOfAppId) {
        List<WebElement> listOfElements = driver.findElements(By.xpath("(//table[@class='table table-striped table-bordered table-hover'])[2]//tbody/tr"));

        compareTwoLists(listOfElements, listOfAppId);
        return this;
    }

    public PendingApprovalPage assertThatCorrectRequestIdIsDisplayedAfterClickingOnParticularRequest(String buttonText) {

        String RequestUUID = driver.findElement(By.xpath("//div[@class='card-body']//div[@class='card-title h5'][text()!='Applications To Be Approved']")).getText();
        assertThat(RequestUUID.equals(buttonText))
                .as("Request Id which is in header Of the table is NOT correct").isTrue();

        return this;
    }

    // Request Approval Pop Up

    public BasePage assertThatRequestApprovalPopUpIsDisplayed() {
        assertThat(requestApprovalPopUp.isDisplayed())
                .as("Request Approval PopUp is not displayed").isTrue();
        return this;
    }


    public BasePage removeClickedElementFromListAndDeleteFromTheList() {
        listOfAppIdInApprovalRequest.remove(clickOneCheckBoxOnThePageAndGetItsId());
        return this;
    }

    //clean

    public PendingApprovalPage deleteItems() {
        pressPendingApprovalHeaderItem();
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//a"));
        if (listOfElements.size() != 0) {
            for (WebElement webElement : listOfElements) {
                webElement.click();
                super.deleteItems();
                wait(3);
            }
        }
        return this;
    }


    public void setUrlForDriver() {
        pressPendingApprovalHeaderItem();
    }

}
