package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PendingRejectionPage extends BasePage {

    @FindBy(xpath = "//div[@class='card-body']//button[text()='Reject All']")
    private WebElement rejectAllButton;


    @FindBy(xpath = "//div[@class='modal-title h4'][text()='Confirm DELETE From Published']")
    WebElement rejectionConfirmationPopUp;

    @FindBy(xpath = "//div[@class='modal-footer']//button[text()='Reject All']")
    private WebElement rejectAllButtonOnRejectionConfirmationPopUp;

    List<String> listOfRestoredAppId = new ArrayList<>();

    List<String> listOfRejectedAppId = new ArrayList<>();


    public List<String> getListOfRestoredAppId() {
        return listOfRestoredAppId;
    }

    public List<String> getListOfRejectedAppId() {
        return listOfRejectedAppId;
    }


    public PendingRejectionPage() {
        super();
        pressPendingRejectionHeaderItem();
    }

    // Reject All Button

    public PendingRejectionPage pressRejectAllButton() {
        rejectAllButton.click();
        wait(3);
        return this;
    }

    public void assertThatRejectAllButtonContainsText() {
        String text = "Reject All";
        assertThat(rejectAllButton.getText())
                .as("Expected text " + text + "was not found").isEqualTo(text);
    }

    public void assertThatRejectAllButtonIsEnabled() {
        assertThat(rejectAllButton.isEnabled())
                .as("Reject All Button is NOT enabled").isTrue();
    }

    public void assertThatRejectAllButtonIsPresentOnThePage() {
        assertThat(rejectAllButton.isDisplayed())
                .as("Reject All button was not found").isTrue();
    }



    // Rejection Confirmation Pop Up

    public PendingRejectionPage pressRejectAllButtonOnRejectionConfirmationPopUp() {
        rejectAllButtonOnRejectionConfirmationPopUp.click();
        wait(3);
        return this;
    }

    // Rejection Conformation Pop Up


    public BasePage assertThatRejectionApprovalPopUpIsNotDisplayed() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='modal-title h4'][text()='Confirm DELETE From Published']"));
        assertThat(listOfElements.size())
                .as("Request Approval Pop Up is displayed while it should NOT").isEqualTo(0);
        return this;
    }

    public BasePage assertThatRejectAllButtonOnRejectionConfirmationPopUpIsEnabled() {
        assertThat(rejectAllButtonOnRejectionConfirmationPopUp.isEnabled())
                .as("Reject All button is NOT enabled on Rejection Confirmation PopUp").isTrue();
        return this;
    }

    public BasePage assertThatRejectAllButtonOnRejectionConfirmationPopUpContainsText() {
        String text = "Reject All";
        assertThat(rejectAllButtonOnRejectionConfirmationPopUp.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }

    public BasePage assertThatRejectAllButtonIsPresentOnRejectionConfirmationPopUp() {
        assertThat(rejectAllButtonOnRejectionConfirmationPopUp.isDisplayed())
                .as("Reject All button was not found on Rejection Confirmation PopUp").isTrue();
        return this;
    }

    public BasePage assertThatRequestApprovalPopUpIsDisplayed() {
        assertThat(rejectionConfirmationPopUp.isDisplayed())
                .as("Rejection Confirmation PopUp is not displayed").isTrue();
        return this;
    }



    public PendingRejectionPage deleteItems() {

        if (isCheckBoxOnThePageUnclicked()) {
            pressRejectAllButton();
            pressRejectAllButtonOnRejectionConfirmationPopUp();
            wait(1);
        }
        return this;
    }

    public void setUrlForDriver() {
        pressPendingRejectionHeaderItem();
    }

    public PendingRejectionPage assertThatNoRejectedAppIdIsDisplayedOnPendingRejectionPage() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr"));

        assertThat(listOfElements.size())
                .as("List Of App Id Is NOT Empty").isEqualTo(0);
        return this;
    }

    public PendingRejectionPage assertThatRejectedAppIdIsDisplayedOnPendingRejectionPage(List<String> listOfAppId) {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr/td"));

        compareTwoLists(listOfElements, listOfAppId);

        return this;

    }

    public PublishedPage generateRejectedItems() {
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();
        PublishedPage publishedPage = new PublishedPage();

        pendingApprovalPage.createAndApproveAppId();
        publishedPage.removeAllPublishedAppId(pendingApprovalPage);

        publishedPage.setListOfApprovedAppIds(pendingApprovalPage.getListOfAppIdInApprovalRequest());
        wait(4);

        pendingApprovalPage.pressPendingRejectionHeaderItem();

        return publishedPage;
    }

    public PendingRejectionPage removeOneRejectedAppId(PublishedPage publishedPage) {
        listOfRestoredAppId = new ArrayList<>();
        String removedAppId = clickOneCheckBoxOnThePageAndGetItsId();
        listOfRestoredAppId.add(removedAppId);
        publishedPage.getListOfRejectedAppIds().remove(removedAppId);
        listOfRejectedAppId = publishedPage.getListOfRejectedAppIds();
        pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp();
        wait(2);
        return this;
    }

    public PendingRejectionPage removeAllRejectedAppId(PublishedPage publishedPage) {
        clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp();

        listOfRestoredAppId = publishedPage.getListOfRejectedAppIds();
        wait(2);
        return this;
    }

}
