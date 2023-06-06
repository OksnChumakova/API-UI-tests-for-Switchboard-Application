package ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.LivePage;
import ui.pages.PendingRejectionPage;
import ui.pages.PublishedPage;

import java.util.ArrayList;
import java.util.List;

public class PendingRejectionPageTest extends BaseTest {

    private PendingRejectionPage pendingRejectionPage;

    @Override
    public  void initPage() {
        pendingRejectionPage = new PendingRejectionPage();
    }

    @Override
    public void logout() {
        pendingRejectionPage.pressLogoutHeaderItem();
    }

    @BeforeMethod
    public void setUpMethod() {
        super.setUpMethod();
        pendingRejectionPage.setUrlForDriver();
    }


    // Remove Button

    @Test
    public void checkIfRemoveButtonIsPresentOnThePage() {
        pendingRejectionPage.assertThatRemoveButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfRemoveButtonContainsTest() {
        pendingRejectionPage.assertThatRemoveButtonContainsText();
    }


    @Test
    public void checkIfRemoveButtonIsEnabled() {
        pendingRejectionPage.assertThatRemoveButtonIsEnabled();
    }

    // Reject All Button

    @Test
    public void checkIfRejectAllButtonIsPresentOnThePage() {
        pendingRejectionPage.assertThatRejectAllButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfRejectAllButtonContainsTest() {
        pendingRejectionPage.assertThatRejectAllButtonContainsText();
    }


    @Test
    public void checkIfRejectAllButtonIsEnabled() {
        pendingRejectionPage.assertThatRejectAllButtonIsEnabled();
    }

    //Remove Confirmation Pop Up

    @Test
    public void checkIfRemovalConfirmationPopUpShowsUp() {
        pendingRejectionPage
                .pressRemoveButton()
                .assertThatRemovalConfirmationPopUpIsDisplayed()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    // Remove Button On Removal Confirmation Pop Up
    @Test
    public void checkIfRemoveButtonIsPresentOnRemovalConfirmationPopUp() {
        pendingRejectionPage
                .pressRemoveButton()
                .assertThatRemoveButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfRemoveButtonOnRemovalConfirmationPopUpContainsText() {
        pendingRejectionPage
                .pressRemoveButton()
                .assertThatRemoveButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    // Cancel Button On Removal Confirmation Pop Up

    @Test
    public void checkIfCancelButtonIsPresentOnRemovalConfirmationPopUp() {
        pendingRejectionPage
                .pressRemoveButton()
                .assertThatCancelButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRemovalConfirmationPopUpContainsText() {
        pendingRejectionPage
                .pressRemoveButton()
                .assertThatCancelButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    // Rejection Approval Pop Up

    @Test
    public void checkIfRejectionApprovalPopUpShowsUp() {

        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .assertThatRequestApprovalPopUpIsDisplayed()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    // Cancel Button On Rejection Approval Pop Up

    @Test
    public void checkIfCancelButtonIsPresentOnRejectionApprovalPopUp() {
        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .assertThatCancelButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRejectionApprovalPopUpContainsText() {
        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .assertThatCancelButtonOnRequestApprovalPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    @Test
    public void checkIfCancelButtonOnRejectionApprovalPopUpIsEnabled() {
        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage
                .pressRejectAllButton()
                .assertThatCancelButtonOnRequestApprovalPopUpIsEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();
    }



    // Close Button On Rejection Approval Pop Up

    @Test
    public void checkIfCloseButtonOnRejectionApprovalPopUpWorks() {

        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .pressCloseButtonOnRequestApprovalPopUp()
                .<PendingRejectionPage>as()
                .assertThatRejectionApprovalPopUpIsNotDisplayed();
    }


    @Test
    public void checkIfCloseButtonIsPresentOnRejectionApprovalPopUp() {
        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .assertThatCloseButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    // Reject All Button On Rejection Approval Pop Up

    @Test
    public void checkIfRejectAllButtonIsPresentOnRejectionApprovalPopUp() {
        pendingRejectionPage
                .generateRejectedItems();
        pendingRejectionPage.pressRejectAllButton()
                .assertThatRejectAllButtonIsPresentOnRejectionConfirmationPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();

    }


    @Test
    public void checkIfRejectAllButtonOnRejectionApprovalPopUpContainsText() {
        pendingRejectionPage
                .generateRejectedItems();

        pendingRejectionPage
                .pressRejectAllButton()
                .assertThatRejectAllButtonOnRejectionConfirmationPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfRejectAllButtonOnRejectionApprovalPopUpIsEnabled() {
        pendingRejectionPage
                .generateRejectedItems();

        pendingRejectionPage
                .pressRejectAllButton()
                .assertThatRejectAllButtonOnRejectionConfirmationPopUpIsEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

// Scenarios

    @Test
    public void checkSingleAppIdRemoving() {

        PublishedPage publishedPage = pendingRejectionPage.generateRejectedItems();
        pendingRejectionPage
                .removeOneRejectedAppId(publishedPage)
                .assertThatRejectedAppIdIsDisplayedOnPendingRejectionPage(pendingRejectionPage.getListOfRejectedAppId())
                .pressPublishedHeaderItem();

        publishedPage.assertThatApprovedAppIdIsOnPublishedPage(pendingRejectionPage.getListOfRestoredAppId());

        LivePage livePage = new LivePage();

        List listOfAllAppId = new ArrayList(pendingRejectionPage.getListOfRestoredAppId());
        listOfAllAppId.addAll(pendingRejectionPage.getListOfRejectedAppId());

        livePage.assertThatApprovedAppIdIsOnLivePage(listOfAllAppId);
    }

    @Test
    public void checkBatchAppIdRemoving() {
        PublishedPage publishedPage = pendingRejectionPage.generateRejectedItems();
        pendingRejectionPage
                .removeAllRejectedAppId(publishedPage)
                .assertThatNoRejectedAppIdIsDisplayedOnPendingRejectionPage()
                .pressPublishedHeaderItem();

        publishedPage.assertThatApprovedAppIdIsOnPublishedPage(pendingRejectionPage.getListOfRestoredAppId());

        LivePage livePage = new LivePage();
        livePage.assertThatApprovedAppIdIsOnLivePage(pendingRejectionPage.getListOfRestoredAppId());


    }

    @Test
    public void checkIfAppIdIsDisplayedAfterRemovalCanceling() {
        PublishedPage publishedPage = pendingRejectionPage.generateRejectedItems();
        pendingRejectionPage

                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressCancelButtonOnRemovalConfirmationPopUp()
                .<PendingRejectionPage>as()
                .assertThatRejectedAppIdIsDisplayedOnPendingRejectionPage(publishedPage.getListOfRejectedAppIds())
                .pressPublishedHeaderItem();

        publishedPage.assertThatNoAppIdIsDisplayedOnPublishedPage();

        LivePage livePage = new LivePage();
        livePage.assertThatApprovedAppIdIsOnLivePage(publishedPage.getListOfRejectedAppIds());


    }

    @Test
    public void checkIfAppIdIsRejected() {
        PublishedPage publishedPage = pendingRejectionPage.generateRejectedItems();

        pendingRejectionPage
                .pressRejectAllButton()
                .pressRejectAllButtonOnRejectionConfirmationPopUp()
                .assertThatNoRejectedAppIdIsDisplayedOnPendingRejectionPage()
                .pressPublishedHeaderItem();

        publishedPage.assertThatNoAppIdIsDisplayedOnPublishedPage();

        LivePage livePage = new LivePage();
        livePage.assertThatNoAppIdIsDisplayedOnLivePage();

    }


}
