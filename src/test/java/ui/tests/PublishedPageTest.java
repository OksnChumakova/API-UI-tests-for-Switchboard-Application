package ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.common_functional.ISearch;
import ui.pages.PendingApprovalPage;
import ui.pages.PendingRejectionPage;
import ui.pages.PublishedPage;

import static constants.Constants.APPLICATION_ID_1;

public class PublishedPageTest extends BaseTest {

    private PublishedPage publishedPage;

    @Override
    public  void initPage() {
        publishedPage = new PublishedPage();
    }

    @Override
    public void logout() {
        publishedPage.pressLogoutHeaderItem();
    }

    @BeforeMethod
    public void setUpMethod() {
        super.setUpMethod();
        publishedPage.setUrlForDriver();
    }

    // Remove Button

    @Test
    public void checkIfRemoveButtonIsPresentOnThePage() {
        publishedPage.assertThatRemoveButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfRemoveButtonContainsTest() {
        publishedPage.assertThatRemoveButtonContainsText();
    }


    @Test
    public void checkIfRemoveButtonIsEnabled() {
        publishedPage.assertThatRemoveButtonIsEnabled();
    }

    // Removal Confirmation Pop Up

    @Test
    public void checkIfRemovalConfirmationPopUpShowsUp() {
        publishedPage
                .pressRemoveButton()
                .assertThatRemovalConfirmationPopUpIsDisplayed()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    // Remove Button On Removal Confirmation Pop Up

    @Test
    public void checkIfRemoveButtonIsPresentOnRemovalConfirmationPopUp() {
        publishedPage
                .pressRemoveButton()
                .assertThatRemoveButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfRemoveButtonOnRemovalConfirmationPopUpContainsText() {
        publishedPage
                .pressRemoveButton()
                .assertThatRemoveButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }


    // Cancel Button On Removal Confirmation Pop Up

    @Test
    public void checkIfCancelButtonIsPresentOnRemovalConfirmationPopUp() {
        publishedPage
                .pressRemoveButton()
                .assertThatCancelButtonIsPresentOnRemovalConfirmationPopUp()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    @Test
    public void checkIfCancelButtonOnRemovalConfirmationPopUpContainsText() {
        publishedPage
                .pressRemoveButton()
                .assertThatCancelButtonOnRemovalConfirmationPopUpContainsText()
                .pressCancelButtonOnRemovalConfirmationPopUp();
    }

    // Scenarios

    @Test
    public void checkIfSingleAppIdIsRemovedFromPublishedPage() {

        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage
                .createAndApproveAppId();

        publishedPage
                .removeOnePublishedAppId(pendingApprovalPage)
                .assertThatApprovedAppIdIsOnPublishedPage(pendingApprovalPage.getListOfAppIdInApprovalRequest());


        PendingRejectionPage pendingRejectionPage = new PendingRejectionPage();
        pendingRejectionPage.assertThatRejectedAppIdIsDisplayedOnPendingRejectionPage(publishedPage.getListOfRejectedAppIds());

    }

    @Test
    public void checkIfBatchAppIdIsRemovedFromPublishedPage() {
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage
                .createAndApproveAppId();

        publishedPage
                .removeAllPublishedAppId(pendingApprovalPage)
                .pressPublishedHeaderItem();

        publishedPage.assertThatNoAppIdIsDisplayedOnPublishedPage();


        PendingRejectionPage pendingRejectionPage = new PendingRejectionPage();
        pendingRejectionPage.assertThatRejectedAppIdIsDisplayedOnPendingRejectionPage(publishedPage.getListOfRejectedAppIds());

    }


    @Test
    public void checkIfAppIdIsNotRemovedFromPublishedPageInCaseRemovalCanceling() {
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();

        pendingApprovalPage.
                createAndApproveAppId()
                .clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressCancelButtonOnRemovalConfirmationPopUp();

        publishedPage.assertThatApprovedAppIdIsOnPublishedPage(pendingApprovalPage.getListOfAppIdInApprovalRequest());

        PendingRejectionPage pendingRejectionPage = new PendingRejectionPage();
        pendingRejectionPage.assertThatNoRejectedAppIdIsDisplayedOnPendingRejectionPage();

    }

    @Test
    public void checkIfItemIsFoundViaSearchField(){
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();
        pendingApprovalPage
                .createAndApproveAppId();
        ISearch.assertThatSearchResultIsCorrect(APPLICATION_ID_1);

    }

    @Test
    public void checkIfItemInUppercaseIsFoundViaSearchField(){
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();
        pendingApprovalPage
                .createAndApproveAppId();
        ISearch.assertThatSearchResultIsCorrect(APPLICATION_ID_1.toUpperCase());


    }

    @Test
    public void checkIfItemIsNotFoundViaSearchField(){
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();
        pendingApprovalPage
                .createAndApproveAppId();
        ISearch.assertThatSearchResultIsNotFound("someNonExistingAppId");

    }

    @Test
    public void checkIfItemIsGoneFromSearchListAfterBeingRemoved(){
        PendingApprovalPage pendingApprovalPage = new PendingApprovalPage();
        pendingApprovalPage
                .createAndApproveAppId();
        publishedPage.assertThatItemIsRemovedFromTheSearchListAfterBeingRemoved(APPLICATION_ID_1);
    }
}
