package ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.common_functional.IConfirmationNotification;
import ui.common_functional.IItemGenerator;
import ui.pages.PendingApprovalPage;
import ui.pages.PendingRejectionPage;
import ui.pages.SubmitNewPage;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.APPLICATION_ID_1;
import static constants.Constants.APPLICATION_ID_2;

public class SubmitNewPageTest extends BaseTest {

    private SubmitNewPage submitNewPage;

    @Override
    public void initPage() {
        submitNewPage = new SubmitNewPage();
    }

    @Override
    public void logout() {
        submitNewPage.pressLogoutHeaderItem();
    }


    @BeforeMethod
    public void setUpMethod() {
        super.setUpMethod();
        submitNewPage.setUrlForDriver();
    }

    // App Input Field

    @Test
    public void checkIfAppInputFieldPresentOnThePage() {
        submitNewPage.assertThatAppInputFieldIsPresentOnThePage();
    }


    // Remove Button
    @Test
    public void checkIfRemoveButtonIsPresentOnThePage() {
        submitNewPage.assertThatRemoveButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfRemoveButtonContainsTest() {
        submitNewPage.assertThatRemoveButtonContainsText();
    }

    @Test
    public void checkIfRemoveButtonIsEnabled() {
        submitNewPage.assertThatRemoveButtonIsEnabled();
    }


    // Add To Approval Request Button
    @Test
    public void checkIfAddToApprovalRequestButtonIsPresentOnThePage() {
        submitNewPage.assertThatAddToApprovalRequestButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfAddToApprovalRequestButtonContainsTest() {
        submitNewPage.assertThatAddToApprovalRequestButtonContainsText();
    }


    @Test
    public void checkIfAddToApprovalRequestButtonIsEnabled() {
        submitNewPage.assertThatAddToApprovalRequestButtonIsEnabled();
    }

    //Request Approval Button

    @Test
    public void checkIfRequestApprovalButtonIsPresentOnThePage() {
        submitNewPage.assertThatRequestApprovalButtonIsPresentOnThePage();
    }


    @Test
    public void checkIfRequestApprovalButtonContainsTest() {
        submitNewPage.assertThatRequestApprovalButtonContainsText();
    }

    @Test
    public void checkIfRequestApprovalButtonIsEnabled() {
        submitNewPage.assertThatRequestApprovalButtonIsEnabled();
    }


    // Header Items

    @Test
    public void checkIfSubmitNewHeaderItemIsPresentOnThePage() {
        submitNewPage.assertThatSubmitNewHeaderItemIsPresentOnThePage();
    }

    @Test
    public void checkIfPendingApprovalHeaderItemIsPresentOnThePage() {
        submitNewPage.assertThatPendingApprovalHeaderItemIsPresentOnThePage();
    }

    @Test
    public void checkIfPendingRejectionHeaderItemIsPresentOnThePage() {
        submitNewPage.assertThatPendingRejectionHeaderItemIsPresentOnThePage();
    }

    @Test
    public void checkIfPublishedHeaderItemIsPresentOnThePage() {
        submitNewPage.assertThatPublishedHeaderItemIsPresentOnThePage();
    }

    @Test
    public void checkIfLiveHeaderItemIsPresentOnThePage() {
        submitNewPage.assertThatLiveHeaderItemIsPresentOnThePage();
    }

    @Test
    public void checkIfSubmitNewHeaderItemContainsTest() {
        submitNewPage.assertThatSubmitNewHeaderItemContainsText();
    }

    @Test
    public void checkIfPendingApprovalHeaderItemContainsTest() {
        submitNewPage.assertThatPendingApprovalHeaderItemContainsText();
    }

    @Test
    public void checkIfPendingRejectionHeaderItemContainsTest() {
        submitNewPage.assertThatPendingRejectionHeaderItemContainsText();
    }

    @Test
    public void checkIfPublishedHeaderItemContainsTest() {
        submitNewPage.assertThatPublishedHeaderItemContainsText();
    }

    @Test
    public void checkIfLiveHeaderItemContainsTest() {
        submitNewPage.assertThatLiveHeaderItemContainsText();
    }

    @Test
    public void checkIfSubmitNewHeaderItemIsEnabled() {
        submitNewPage.assertThatSubmitNewHeaderItemIsEnabled();
    }

    @Test
    public void checkIfPendingApprovalHeaderItemIsEnabled() {
        submitNewPage.assertThatPendingApprovalHeaderItemIsEnabled();
    }

    @Test
    public void checkIfPendingRejectionHeaderItemIsEnabled() {
        submitNewPage.assertThatPendingRejectionHeaderItemIsEnabled();
    }

    @Test
    public void checkIfPublishedHeaderItemIsEnabled() {
        submitNewPage.assertThatPublishedHeaderItemIsEnabled();
    }

    @Test
    public void checkIfLiveHeaderItemIsEnabled() {
        submitNewPage.assertThatLiveHeaderItemIsEnabled();
    }


    // Removal Confirmation Pop Up
    @Test
    public void checkIfRemovalConfirmationPopUpShowsUp() {
        submitNewPage
                .pressRemoveButton()
                .assertThatRemovalConfirmationPopUpIsDisplayed()
                .pressCancelButtonOnRemovalConfirmationPopUp();

    }

    // Remove Button On Removal Confirmation Pop Up

    @Test
    public void checkIfRemoveButtonIsPresentOnRemovalConfirmationPopUp() {
        submitNewPage
                .pressRemoveButton()
                .assertThatRemoveButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();

    }

    @Test
    public void checkIfRemoveButtonOnRemovalConfirmationPopUpContainsText() {
        submitNewPage
                .pressRemoveButton()
                .assertThatRemoveButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfRemoveButtonOnRemovalConfirmationPopUpIsEnabled() {
        submitNewPage
                .pressRemoveButton()
                .assertThatRemoveButtonIsEnablesOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    // Cancel Button On Removal Confirmation Pop Up

    @Test
    public void checkIfCancelButtonIsPresentOnRemovalConfirmationPopUp() {
        submitNewPage
                .pressRemoveButton()
                .assertThatCancelButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    @Test
    public void checkIfCancelButtonOnRemovalConfirmationPopUpContainsText() {
        submitNewPage
                .pressRemoveButton()
                .assertThatCancelButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    @Test
    public void checkIfCancelButtonOnRemovalConfirmationPopUpIsEnabled() {
        submitNewPage
                .pressRemoveButton()
                .assertThatCancelButtonIsEnablesOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

// Request Approval Pop Up

    @Test
    public void checkIfRequestApprovalPopUpShowsUp() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatRequestApprovalPopUpIsDisplayed()
                .pressCancelButtonOnRequestApprovalPopUp();

    }

    // Request Approval Button On Request Approval Pop Up

    @Test
    public void checkIfRequestApprovalButtonIsPresentOnRequestApprovalPopUp() {
        submitNewPage

                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatRequestApprovalButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfRequestApprovalButtonOnRequestApprovalPopUpContainsText() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatRequestApprovalButtonOnRequestApprovalPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();
    }


    @Test
    public void checkIfRequestApprovalButtonOnRequestApprovalPopUpIsEnabled() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatRequestApprovalButtonOnRequestApprovalPopUpIsEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();
    }


    // Cancel Button On Request Approval Pop Up


    @Test
    public void checkIfCancelButtonIsPresentOnRequestApprovalPopUp() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatCancelButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRequestApprovalPopUpContainsText() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatCancelButtonOnRequestApprovalPopUpContainsText()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRequestApprovalPopUpIsEnabled() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatCancelButtonOnRequestApprovalPopUpIsEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    // Close Button On Request Approval Pop Up


    @Test
    public void checkIfCloseButtonIsPresentOnRequestApprovalPopUp() {
        submitNewPage

                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatCloseButtonIsPresentOnRequestApprovalPopUp()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCloseButtonOnRequestApprovalPopUpIsEnabled() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .assertThatCloseButtonOnRequestApprovalPopUpisEnabled()
                .pressCancelButtonOnRequestApprovalPopUp();
    }

    @Test
    public void checkIfCloseButtonOnRequestApprovalPopUpWorks() {
        submitNewPage

                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressCloseButtonOnRequestApprovalPopUp()
                .assertThatRequestApprovalPopUpIsNotDisplayed();
    }

    // Scenarios

    @Test
    public void checkIfSingleAppIdIsRemovedFromApprovalRequestAfterRemoval() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp()
                .<SubmitNewPage>as()
                .assertThatTableWithAddedAppIdIsEmpty();
    }

    @Test
    public void checkIfSingleAppIdOutOfTwoIsRemovedFromApprovalRequestAfterRemoval() {
        List<String> listOfAddedAppId = new ArrayList<>();
        listOfAddedAppId.add(APPLICATION_ID_1);
        listOfAddedAppId.add(APPLICATION_ID_2);
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_2)
                .pressAddToApprovalRequestButton();
                String checkedCheckbox = submitNewPage.clickOneCheckBoxOnThePageAndGetItsId();
                listOfAddedAppId.remove(checkedCheckbox);
                submitNewPage.pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp()
                .<SubmitNewPage>as()
                .asserThatAddedAppIdIsPresentInTheList(listOfAddedAppId.get(0));

    }

    @Test
    public void checkIfBatchAppIdIsRemovedFromApprovalRequestAfterRemoval() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_2)
                .pressAddToApprovalRequestButton()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp()
                .<SubmitNewPage>as()
                .assertThatTableWithAddedAppIdIsEmpty();
    }

    @Test()
    public void checkIfAppIdIsNotRemovedFromApprovalRequestAfterRemovalCanceling() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressCancelButtonOnRemovalConfirmationPopUp()
                .<SubmitNewPage>as()
                .assertThatAddedAppIdIsPresentOnThePage()
                .assertThatAddedAppIdContainsText(APPLICATION_ID_1);
    }


    @Test
    public void checkIfSingleAppIdIsAddedToApprovalRequest() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .assertThatAddedAppIdIsPresentOnThePage();
    }

    @Test
    public void checkIfBatchAppIdIsAddedToApprovalRequest() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_2)
                .pressAddToApprovalRequestButton()
                .assertThatTwoAddedAppIdAreDisplayedOnThePage();
    }

    @Test
    public void checkIfAppIdIsDistinctInApprovalRequestAfterAttemptToAddSameAppId() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .assertThatAddedAppIdIsDistinctOnThePage();
    }

    @Test
    public void checkIfAppIdIsNotAddedToRequestApprovalInCaseItIsAlreadyInPendingApproval() {

        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressRequestApprovalButtonOnRequestApprovalPopUp()
                .<SubmitNewPage>as()
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .assertThatTableWithAddedAppIdIsEmpty();

    }

    @Test
    public void checkIfAppIdIsNotAddedToRequestApprovalInCaseItIsAlreadyPublished() {

        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage.
                createAndApproveAppId();

        submitNewPage.pressSubmitNewHeaderItem()
                .<SubmitNewPage>as()
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .assertThatTableWithAddedAppIdIsEmpty();

    }


    @Test
    public void checkIfAppIdIsNotAddedToRequestApprovalInCaseItIsAlreadyInPendingRejection() {

        PendingRejectionPage pendingRejectionPage = new PendingRejectionPage();

        pendingRejectionPage
                .generateRejectedItems();
        submitNewPage
                .pressSubmitNewHeaderItem()
                .<SubmitNewPage>as()
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .assertThatTableWithAddedAppIdIsEmpty();

    }

    @Test()
    public void checkIfApprovedRequestIsNotCreatedWithoutConfirming() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressCancelButtonOnRequestApprovalPopUp()
                .<SubmitNewPage>as()
                .assertThatAddedAppIdIsPresentOnThePage()
                .assertThatAddedAppIdContainsText(APPLICATION_ID_1);
    }


    @Test
    public void checkIfBatchApprovalRequestIsCreatedOnPendingForApprovalPage() {

        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_2)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressRequestApprovalButtonOnRequestApprovalPopUp();

        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage
                .assertThatAddedRequestForApprovalIsDisplayedOnPendingApprovalPage(submitNewPage.getDateOfRequest());

        pendingApprovalPage.pressSubmitNewHeaderItem();

    }


    @Test
    public void checkIfApprovalRequestOfOneAppIdIsCreatedOnPendingForApprovalPage() {

        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pressRequestApprovalButton()
                .pressRequestApprovalButtonOnRequestApprovalPopUp()
                .assertThatTableWithAddedAppIdIsEmpty();

        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage
                .assertThatAddedRequestForApprovalIsDisplayedOnPendingApprovalPage(submitNewPage.getDateOfRequest());


    }

    @Test
    public void checkIfApprovalRequestIsNotCreatedIfNoAppIdIsAdded() {
        submitNewPage
                .pressRequestApprovalButton();

        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage
                .assertThatNoRequestsAreDisplayedOnPendingApprovalPage();

    }

    @Test
    public void checkIfFifteenItemsFitOnePage() {
        submitNewPage
                .generateItems(15);
        IItemGenerator.assertThatItemsFitOnePage();
    }

    @Test
    public void checkIfTwentyItemsFitTwoPages() {
        submitNewPage
                .generateItems(20);
        IItemGenerator.assertThatItemsFitTwoPages();
    }

    @Test
    public void checkIfAppIdSubmitOptionIsCaseInsensitive() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .pasteValueToAppInputField(APPLICATION_ID_1.toUpperCase())
                .pressAddToApprovalRequestButton()
                .assertThatAddedAppIdIsDistinctOnThePage();
    }

    @Test
    public void checkIfAlertMessageIsCorrectAfterAppIdAdding() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton();
        IConfirmationNotification.assertThatConfirmationNotificationShowsUp("Add Succeeded!");
    }

    @Test
    public void checkIfAlertMessageIsCorrectAfterAppIdRemoval() {
        submitNewPage
                .pasteValueToAppInputField(APPLICATION_ID_1)
                .pressAddToApprovalRequestButton()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp();
        IConfirmationNotification.assertThatConfirmationNotificationShowsUp("Delete Succeeded!");
    }

}
