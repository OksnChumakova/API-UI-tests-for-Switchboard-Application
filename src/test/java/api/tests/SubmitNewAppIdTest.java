package api.tests;

import api.steps.PendingApprovalStep;
import api.steps.SubmitNewAppIdStep;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.util.List;

import static constants.Constants.*;
import static api.steps.PublishedStep.generatePublishedAppId;
import static api.steps.RejectedStep.generateRejectedAppId;

public class SubmitNewAppIdTest extends BaseTest {

    SubmitNewAppIdStep submitNewAppIdStep = new SubmitNewAppIdStep();


    @Test
    public void checkIfSingleAppIdIsAddedToApprovalRequest() {
        Response response = submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);
        assertThatResponseCodeIsCorrect(response, 200);

        List<String> listOfAppIdInRequestForApproval = submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID);
        assertThatResponseBodyIsCorrect(listOfAppIdInRequestForApproval, submitNewAppIdStep.listOfAddedToApprovalRequestAppId);
    }

    @Test
    public void checkIfTwoAppIdAreAddedToApprovalRequest() {
        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1, APPLICATION_ID_2);

        List<String> listOfAppIdInRequestForApproval = submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID);

        assertThatResponseBodyIsCorrect(listOfAppIdInRequestForApproval, submitNewAppIdStep.listOfAddedToApprovalRequestAppId);
    }

    @Test
    public void checkIfAppIdIsNotAddedToApprovalRequestIfItIsInIt() {
        Response response = submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1, APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "Application " + APPLICATION_ID_1 + " is already in the approval request!");
    }


    @Test
    public void checkIfAppIdIsNotAddedToApprovalRequestIfItIsPendingApproval() {

        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        PendingApprovalStep.generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);

        Response response = submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "Application " + APPLICATION_ID_1 + " is in the pending approval!");
    }

    @Test
    public void checkIfAppIdIsNotAddedToApprovalRequestIfItIsPublished() {

        generatePublishedAppId();

        Response response = submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "Application " + APPLICATION_ID_1 + " is already approved!");
    }

    @Test
    public void checkIfAppIdIsNotAddedToApprovalRequestIfItIsPendingRejection() {

        generateRejectedAppId();

        Response response = submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "Application " + APPLICATION_ID_1 + " is in the rejection request!");
    }

    @Test
    public void checkIfSingleAppIdIsDeletedFromApprovalRequest() {

        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);

        Response response = submitNewAppIdStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_REQUEST_FOR_APPROVAL
                , submitNewAppIdStep.listOfRemovedFromApprovalRequestAppId
                , submitNewAppIdStep.listOfAddedToApprovalRequestAppId
                , APPLICATION_ID_1);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListsOfAppIdAreEqual(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID)
                , submitNewAppIdStep.listOfAddedToApprovalRequestAppId);
    }

    @Test
    public void checkIfBatchAppIdIsDeletedFromApprovalRequest() {

        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1, APPLICATION_ID_2);

        Response response = submitNewAppIdStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_REQUEST_FOR_APPROVAL
                , submitNewAppIdStep.listOfRemovedFromApprovalRequestAppId
                , submitNewAppIdStep.listOfAddedToApprovalRequestAppId
                , APPLICATION_ID_1
                , APPLICATION_ID_2);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID));
    }

    @Test
    public void checkIfSingleAppIdIsInPendingApprovalList() {
        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1);

        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();

        Response response = submitNewAppIdStep.approveAppId(pendingApprovalStep);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID));

        assertThatListsOfAppIdAreEqual(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID + "&requestId="
                        + pendingApprovalStep.getListOfPendingApprovalRequests().get(0))
                , pendingApprovalStep.listOfPendingApprovalAppId);
    }

    @Test
    public String checkIfBatchAppIdIsInPendingApprovalList() {
        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_1, APPLICATION_ID_2);

        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();

        Response response = submitNewAppIdStep.approveAppId(pendingApprovalStep);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID));

        assertThatListsOfAppIdAreEqual(submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID + "&requestId="
                        + pendingApprovalStep.getListOfPendingApprovalRequests().get(0))
                , pendingApprovalStep.listOfPendingApprovalAppId);

        return pendingApprovalStep.getListOfPendingApprovalRequests().get(0);

    }

    @Test
    public void checkIfTwoBatchesAreInPendingApprovalList() {

        String requestThatIsChecked = checkIfBatchAppIdIsInPendingApprovalList();

        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, APPLICATION_ID_3
                , APPLICATION_ID_4);

        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();

        Response response = submitNewAppIdStep.approveAppId(pendingApprovalStep);
        assertThatResponseCodeIsCorrect(response, 200);

        List<String> listOfRequestsUpdated = pendingApprovalStep.getListOfPendingApprovalRequests();
        listOfRequestsUpdated.remove(requestThatIsChecked);

        List<String> listOfPendingApprovalAppIdNew = submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID + "&requestId="
                + listOfRequestsUpdated.get(0));

        assertThatListsOfAppIdAreEqual(listOfPendingApprovalAppIdNew, pendingApprovalStep.listOfPendingApprovalAppId);

        List<String> listOfAppIdInRequestForApprovalNew = submitNewAppIdStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID);
        assertThatListIsEmpty(listOfAppIdInRequestForApprovalNew);

    }
}


