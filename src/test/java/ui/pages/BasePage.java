package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public abstract class BasePage {

    @FindBy(xpath = "//div[@class='card-body']//button[text()='Remove']")
    WebElement removeButton;


    //Removal Confirmation Pop Up

    @FindBy(xpath = "//div[text()='Confirm Removal From List']")
    WebElement removalConfirmationPopUp;

    @FindBy(xpath = "//div[@class='fade modal show']//div[@class='modal-footer']//button[@class='btn btn-danger']")
    WebElement removeButtonOnRemovalConfirmationPopUp;

    @FindBy(xpath = "//div[@class='fade modal show']//div[@class='modal-footer']//button[@class='btn btn-secondary']")
    WebElement cancelButtonOnRemovalConfirmationPopUp;


    // Request Approval Pop Up

    @FindBy(xpath = "//div[@class='modal-dialog']//button[@class='btn btn-danger']")
    WebElement approveButtonOnRequestApprovalPopUp;

    @FindBy(xpath = "//div[@class='modal-dialog']//button[@class='btn btn-secondary']")
    WebElement cancelButtonOnRequestApprovalPopUp;

    @FindBy(xpath = "//div/div/div//button[@class='close']")
    WebElement closeButtonOnRequestApprovalPopUp;


//  Header Items

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/submit']")
    WebElement submitNewHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/pending']")
    WebElement pendingApprovalHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/reject']")
    WebElement pendingRejectionHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/published']")
    WebElement publishedHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/live']")
    WebElement liveHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/import']")
    WebElement importHeaderItem;

    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/logout']")
    WebElement logoutHeaderItem;

    @FindBy(xpath = "//input[@type='checkbox'][@id='checkAll']")
    WebElement checkAllCheckBox;


    String cssSelectorForNotCheck = ("input[type='checkbox']:not(:checked)");

    public static WebDriver driver;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    boolean isElementFound(By by, int timeout) {
        List<WebElement> elements = driver.findElements(by);
        for (int i = 0; (i < timeout) && (elements.size() == 0); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elements = driver.findElements(by);
        }
        return elements.size() > 0;
    }

    //Header Items

    public BasePage assertThatSubmitNewHeaderItemIsPresentOnThePage() {
        assertThat(submitNewHeaderItem.isDisplayed())
                .as("Submit New Header Item element was not found").isTrue();
        return this;
    }

    public BasePage assertThatPendingApprovalHeaderItemIsPresentOnThePage() {
        assertThat(pendingApprovalHeaderItem.isDisplayed())
                .as("Pending Approval Header Item element was not found").isTrue();
        return this;
    }

    public BasePage assertThatPendingRejectionHeaderItemIsPresentOnThePage() {
        assertThat(pendingRejectionHeaderItem.isDisplayed())
                .as("Pending Rejection Header Item element was not found").isTrue();
        return this;
    }

    public BasePage assertThatPublishedHeaderItemIsPresentOnThePage() {
        assertThat(publishedHeaderItem.isDisplayed())
                .as("pendingRejectionHeaderItem element was not found").isTrue();
        return this;
    }

    public BasePage assertThatLiveHeaderItemIsPresentOnThePage() {
        assertThat(liveHeaderItem.isDisplayed())
                .as("Live Header Item element was not found").isTrue();
        return this;
    }

    public BasePage assertThatSubmitNewHeaderItemContainsText() {
        String text = "Submit New";
        assertThat(submitNewHeaderItem.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatPendingApprovalHeaderItemContainsText() {
        String text = "Pending Approval";
        assertThat(pendingApprovalHeaderItem.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatPendingRejectionHeaderItemContainsText() {
        String text = "Pending Rejection";
        assertThat(pendingRejectionHeaderItem.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatPublishedHeaderItemContainsText() {
        String text = "Published";
        assertThat(publishedHeaderItem.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatLiveHeaderItemContainsText() {
        String text = "Live";
        assertThat(liveHeaderItem.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatPendingApprovalHeaderItemIsEnabled() {
        assertThat(pendingApprovalHeaderItem.isEnabled())
                .as("Pending Approval Header Item is NOT enabled").isTrue();
        return this;
    }

    public BasePage assertThatPendingRejectionHeaderItemIsEnabled() {
        assertThat(submitNewHeaderItem.isEnabled())
                .as("Pending Rejection Header Item is NOT enabled").isTrue();
        return this;
    }

    public BasePage assertThatSubmitNewHeaderItemIsEnabled() {
        assertThat(submitNewHeaderItem.isEnabled())
                .as("Submit New Header Item is NOT enabled").isTrue();
        return this;
    }

    public BasePage assertThatPublishedHeaderItemIsEnabled() {
        assertThat(publishedHeaderItem.isEnabled())
                .as("Published Header Item is NOT enabled").isTrue();
        return this;
    }

    public BasePage assertThatLiveHeaderItemIsEnabled() {

        assertThat(liveHeaderItem.isEnabled())
                .as("Live Header Item is NOT enabled").isTrue();
        return this;
    }

// Click On Header Items

    public BasePage pressPendingApprovalHeaderItem() {
        pendingApprovalHeaderItem.click();
        wait(1);
        return this;
    }

    public BasePage pressSubmitNewHeaderItem() {
        submitNewHeaderItem.click();
        wait(1);
        return this;
    }

    public BasePage pressPendingRejectionHeaderItem() {
        pendingRejectionHeaderItem.click();
        wait(1);
        return this;
    }

    public BasePage pressPublishedHeaderItem() {
        publishedHeaderItem.click();
        wait(1);
        return this;
    }

    public BasePage pressImportHeaderItem() {
        importHeaderItem.click();
        wait(1);
        return this;
    }

    public void pressLiveHeaderItem() {
        liveHeaderItem.click();
        wait(1);
    }

    public void pressLogoutHeaderItem() {

        logoutHeaderItem.click();
        wait(2);
    }


    public BasePage deleteItems() {
        if (isCheckBoxOnThePageUnclicked()) {
            clickCheckAllCheckbox()
                    .pressRemoveButton()
                    .pressRemoveButtonOnRemovalConfirmationPopUp();
            wait(1);
        }
        return this;
    }


    public String clickOneCheckBoxOnThePageAndGetItsId() {
        String cssSelectorForNotCheck = ("(//div[@class='form-check form-check-inline']//input[@type='checkbox'])[2]");
        String checkBoxMarked = "";
        List<WebElement> checkBoxList = driver.findElements(By.xpath(cssSelectorForNotCheck));
        if (checkBoxList.size() >= 1) {
            for (int i = 0; i < checkBoxList.size(); i++) {
                checkBoxList.get(i).click();
                checkBoxMarked = checkBoxList.get(i).getAttribute("id");

            }
        }
        return checkBoxMarked;
    }

    public BasePage clickCheckAllCheckbox() {
        wait(1);
        checkAllCheckBox.click();
        return this;
    }

    public boolean isCheckBoxOnThePageUnclicked() {

        List<WebElement> checkBoxList = driver.findElements(By.cssSelector(cssSelectorForNotCheck));
        if (checkBoxList.size() >= 1) {
            return true;
        }
        return false;
    }


    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Removal Confirmation Pop Up

    public BasePage assertThatRemovalConfirmationPopUpIsDisplayed() {
        assertThat(removalConfirmationPopUp.isDisplayed())
                .as("Removal Confirmation PopUp is not displayed").isTrue();
        return this;
    }


    public BasePage pressRemoveButtonOnRemovalConfirmationPopUp() {
        wait(2);
        removeButtonOnRemovalConfirmationPopUp.click();
        wait(2);
        return this;
    }

    public BasePage pressCancelButtonOnRemovalConfirmationPopUp() {
        cancelButtonOnRemovalConfirmationPopUp.click();
        wait(2);
        return this;
    }

// Remove button

    public BasePage pressRemoveButton() {
        removeButton.click();
        wait(1);
        return this;
    }

    public BasePage assertThatRemoveButtonContainsText() {
        String text = "Remove";
        assertThat(removeButton.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }


    public BasePage assertThatRemoveButtonIsPresentOnThePage() {
        assertThat(removeButton.isDisplayed())
                .as("Remove Button element was not found").isTrue();
        return this;
    }

    public BasePage assertThatRemoveButtonIsEnabled() {
        assertThat(removeButton.isEnabled())
                .as("Remove Button is NOT enabled").isTrue();
        return this;
    }


    public BasePage assertThatCancelButtonOnRemovalConfirmationPopUpContainsText() {
        String text = "Cancel";
        assertThat(cancelButtonOnRemovalConfirmationPopUp.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatRemoveButtonOnRemovalConfirmationPopUpContainsText() {
        String text = "Remove";
        assertThat(removeButtonOnRemovalConfirmationPopUp.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatRemoveButtonIsPresentOnRemovalConfirmationPopUp() {
        assertThat(removeButtonOnRemovalConfirmationPopUp.isDisplayed())
                .as("Remove button was not found on Removal Confirmation PopUp").isTrue();
        return this;
    }

    public BasePage assertThatCancelButtonIsPresentOnRemovalConfirmationPopUp() {
        assertThat(cancelButtonOnRemovalConfirmationPopUp.isDisplayed())
                .as("Cancel button was not found on Removal Confirmation PopUp").isTrue();
        return this;
    }

    public BasePage assertThatRemoveButtonIsEnablesOnRemovalConfirmationPopUp() {
        assertThat(removeButtonOnRemovalConfirmationPopUp.isDisplayed())
                .as("Remove button is NOT enabled on Removal Confirmation PopUp").isTrue();
        return this;
    }

    public BasePage assertThatCancelButtonIsEnablesOnRemovalConfirmationPopUp() {
        assertThat(cancelButtonOnRemovalConfirmationPopUp.isDisplayed())
                .as("Cancel button is NOT enabled on Removal Confirmation PopUp").isTrue();
        return this;
    }


    // Convert Base Page To One Of Its Child ui.pages

    public <T extends BasePage> T as() {
        return (T) this;
    }


    // Request Approval Pop Up

    public BasePage pressCancelButtonOnRequestApprovalPopUp() {
        wait(2);
        cancelButtonOnRequestApprovalPopUp.click();
        wait(4);
        return this;
    }


    public BasePage assertThatRequestApprovalPopUpIsNotDisplayed() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[text()='Sending Applications to be Approved']"));
        assertThat(listOfElements.size())
                .as("Request Approval Pop Up is displayed while it should NOT").isEqualTo(0);
        return this;
    }

    public BasePage assertThatCancelButtonOnRequestApprovalPopUpContainsText() {
        String text = "Cancel";
        assertThat(cancelButtonOnRequestApprovalPopUp.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatCancelButtonOnRequestApprovalPopUpIsEnabled() {
        assertThat(cancelButtonOnRequestApprovalPopUp.isEnabled())
                .as("Cancel Button on Request Approval Pop Up is NOT Enabled").isTrue();
        return this;
    }

    public BasePage assertThatCloseButtonOnRequestApprovalPopUpisEnabled() {
        assertThat(cancelButtonOnRequestApprovalPopUp.isEnabled())
                .as("Close Button on Request Approval Pop Up is NOT Enabled").isTrue();
        return this;
    }

    public BasePage assertThatApproveButtonOnRequestApprovalPopUpIsEnabled() {
        assertThat(approveButtonOnRequestApprovalPopUp.isEnabled())
                .as("Approve Button on Request Approval Pop Up is NOT Enabled").isTrue();
        return this;
    }

    public BasePage assertThatCancelButtonIsPresentOnRequestApprovalPopUp() {
        assertThat(cancelButtonOnRequestApprovalPopUp.isDisplayed())
                .as("Cancel button was not found on Request Approval PopUp").isTrue();
        return this;
    }

    public BasePage assertThatCloseButtonIsPresentOnRequestApprovalPopUp() {
        assertThat(closeButtonOnRequestApprovalPopUp.isDisplayed())
                .as("Cancel button was not found on Request Approval PopUp").isTrue();
        return this;
    }

    public BasePage pressCloseButtonOnRequestApprovalPopUp() {
        closeButtonOnRequestApprovalPopUp.click();
        wait(2);
        return this;
    }

    public BasePage pressApproveButtonOnRequestApprovalPopUp() {
        approveButtonOnRequestApprovalPopUp.click();
        wait(1);
        return this;
    }

    public BasePage assertThatApproveButtonOnRequestApprovalPopUpContainsText() {
        String text = "Approve";
        assertThat(approveButtonOnRequestApprovalPopUp.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatApproveButtonIsPresentOnRequestApprovalPopUp() {
        assertThat(approveButtonOnRequestApprovalPopUp.isDisplayed())
                .as("Approve button was not found on Request Approval PopUp").isTrue();
        return this;
    }

    public void compareTwoLists(List<WebElement> listOfActualElements, List<String> listOfExpectedAppId) {

        List<String> listOfActualAppId = new ArrayList<>();

        for (WebElement webElement : listOfActualElements) {
            String text = webElement.getText();
            if (text != null && !text.trim().isEmpty() && !text.matches(".* of .*")) {
                listOfActualAppId.add(text);
            }
        }
        Collections.sort(listOfActualAppId);
        Collections.sort(listOfExpectedAppId);


        assertThat(listOfActualAppId.equals(listOfExpectedAppId))
                .as("List Of App Id Is NOT Correct").isTrue();
    }

}
