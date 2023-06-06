package ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.LivePage;
import ui.pages.PendingApprovalPage;
import ui.pages.PublishedPage;

public class PendingApprovalPageTest extends BaseTest {

    private PendingApprovalPage pendingApprovalPage;

    @Override
    public void initPage() {
        pendingApprovalPage = new PendingApprovalPage();
    }

    @Override
    public void logout() {
        pendingApprovalPage.pressLogoutHeaderItem();
    }

    @BeforeMethod
    public void setUpMethod() {
        super.setUpMethod();
        pendingApprovalPage.setUrlForDriver();
    }

// Remove Button

    @Test
    public void checkIfRemoveButtonIsPresentOnThePage() {
        pendingApprovalPage.assertThatRemoveButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfRemoveButtonContainsText() {
        pendingApprovalPage.assertThatRemoveButtonContainsText();
    }

    @Test
    public void checkIfRemoveButtonIsEnabled() {
        pendingApprovalPage.assertThatRemoveButtonIsEnabled();
    }


    // Approve Button

    @Test
    public void checkIfApproveButtonIsPresentOnThePage() {
        pendingApprovalPage.assertThatApproveButtonIsPresentOnThePage();
    }


    @Test
    public void checkIfApproveButtonContainsTest() {
        pendingApprovalPage.assertThatApproveButtonContainsText();
    }

    @Test
    public void checkIfApproveButtonIsEnabled() {
        pendingApprovalPage.assertThatApproveButtonIsEnabled();
    }


    // Removal Confirmation Pop Up

    @Test
    public void checkIfRemovalConfirmationPopUpShowsUp() {
        pendingApprovalPage
                .pressRemoveButton()
                .assertThatRemovalConfirmationPopUpIsDisplayed()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    // Remove Button On Removal Confirmation Pop Up

    @Test
    public void checkIfRemoveButtonIsPresentOnRemovalConfirmationPopUp() {
        pendingApprovalPage
                .pressRemoveButton()
                .assertThatRemoveButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfRemoveButtonOnRemovalConfirmationPopUpContainsText() {
        pendingApprovalPage
                .pressRemoveButton()
                .assertThatRemoveButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    // Cancel Button On Removal Confirmation Pop Up

    @Test
    public void checkIfCancelButtonIsPresentOnRemovalConfirmationPopUp() {
        pendingApprovalPage
                .pressRemoveButton()
                .assertThatCancelButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRemovalConfirmationPopUpContainsText() {
        pendingApprovalPage
                .pressRemoveButton()
                .assertThatCancelButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    // Request Approval Pop Up

    @Test
    public void checkIfRequestApprovalPopUpShowsUp() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatRequestApprovalPopUpIsDisplayed()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    // Approve Button On Request Approval Pop Up


    @Test
    public void checkIfApproveButtonOnRequestApprovalPopUpContainsText() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatApproveButtonOnRequestApprovalPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();

        pendingApprovalPage.pressPendingApprovalHeaderItem();
    }


    @Test
    public void checkIfApproveButtonIsPresentOnRequestApprovalPopUp() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatApproveButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();

    }


    @Test
    public void checkIfApproveButtonIsEnabledOnRequestApprovalPopUp() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatApproveButtonOnRequestApprovalPopUpIsEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    // Cancel Button On Request Approval Pop Up
    @Test
    public void checkIfCancelButtonIsPresentOnRequestApprovalPopUp() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatCancelButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRequestApprovalPopUpContainsText() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatCancelButtonOnRequestApprovalPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();
    }


    // Close Button On Request Approval Pop Up
    @Test
    public void checkIfCloseButtonIsPresentOnRequestApprovalPopUp() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .assertThatCloseButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCloseButtonOnRequestApprovalPopUpWorks() {

        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .pressCloseButtonOnRequestApprovalPopUp()
                .assertThatRequestApprovalPopUpIsNotDisplayed();

    }

    // Scenarios
    @Test
    public void checkIfApprovalRequestContainsCorrectAppId() {
        pendingApprovalPage
                .createApprovalRequest()
                .assertThatAddedAppIdIsPresentInApprovalRequest();

        pendingApprovalPage.pressPendingApprovalHeaderItem();
    }



    @Test
    public void checkIfApprovalRequestIsDisplayedIfNotAllAppIdAreRemovedFromApprovalRequest() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .<PendingApprovalPage>as()
                .removeClickedElementFromListAndDeleteFromTheList()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp()
                .<PendingApprovalPage>as()
                .assertThatAddedAppIdIsPresentInApprovalRequest();
    }

    @Test
    public void checkIfApprovalRequestIsNotDisplayedIfAllAppIdAreRemovedFromApprovalRequest() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp()
                .<PendingApprovalPage>as()
                .assertThatNoRequestsAreDisplayedOnPendingApprovalPage();
    }

    @Test
    public void checkIfApprovedAppIdIsDisplayedOnPublishedPage() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .pressApproveButtonOnRequestApprovalPopUp()
                .<PendingApprovalPage>as()
                .assertThatNoRequestsAreDisplayedOnPendingApprovalPage();

        PublishedPage publishedPage = new PublishedPage();
        publishedPage.assertThatApprovedAppIdIsOnPublishedPage(pendingApprovalPage.getListOfAppIdInApprovalRequest());
    }

    @Test
    public void checkIfApprovedAppIdIsDisplayedOnLivePage() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .pressApproveButtonOnRequestApprovalPopUp()
                .<PendingApprovalPage>as()
                .assertThatNoRequestsAreDisplayedOnPendingApprovalPage();

        LivePage livePage = new LivePage();
        livePage.assertThatApprovedAppIdIsOnLivePage(pendingApprovalPage.getListOfAppIdInApprovalRequest());

    }

    @Test
    public void checkIfRequestIsNotApprovedInCaseOfApprovalCanceling() {
        pendingApprovalPage
                .createApprovalRequest()
                .clickUUIDRequestButton()
                .pressApproveButton()
                .pressCancelButtonOnRequestApprovalPopUp()
                .<PendingApprovalPage>as()
                .assertThatAddedAppIdIsPresentInApprovalRequest();

        PublishedPage publishedPage = new PublishedPage();
        publishedPage.assertThatNoAppIdIsDisplayedOnPublishedPage();


    }

}
