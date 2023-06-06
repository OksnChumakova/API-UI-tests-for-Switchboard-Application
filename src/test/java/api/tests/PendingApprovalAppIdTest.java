package api.tests;

import api.steps.PendingApprovalStep;
import api.steps.PublishedStep;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.util.List;

import static api.steps.PendingApprovalStep.generatePendingApprovalRequest;
import static constants.Constants.*;

public class PendingApprovalAppIdTest extends BaseTest {

    @Test
    public void checkIfSingleAppIdIsDeletedFromApprovalRequest() {
        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);
        Response response = pendingApprovalStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_PENDING_APPROVAL_LIST
                , pendingApprovalStep.listOfRemovedFromPendingApprovalAppId
                , pendingApprovalStep.listOfPendingApprovalAppId
                , APPLICATION_ID_1);
        assertThatResponseCodeIsCorrect(response, 200);

        String requestId = pendingApprovalStep.getListOfPendingApprovalRequests().get(0);
        assertThatListsOfAppIdAreEqual(pendingApprovalStep.getListOfPendingApprovalAppIdPerRequest(requestId), pendingApprovalStep.listOfPendingApprovalAppId);
    }

    @Test
    public void checkIfBatchAppIdIsDeletedFromApprovalRequest() {
        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);
        Response response = pendingApprovalStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_PENDING_APPROVAL_LIST
                , pendingApprovalStep.listOfRemovedFromPendingApprovalAppId
                , pendingApprovalStep.listOfPendingApprovalAppId
                , APPLICATION_ID_1
                , APPLICATION_ID_2);

        assertThatResponseCodeIsCorrect(response, 200);
        assertThatListIsEmpty(pendingApprovalStep.getListOfPendingApprovalRequests());
    }


    @Test
    public String checkIfOneBatchIsApproved() {
        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);

        String requestId = pendingApprovalStep.getListOfPendingApprovalRequests().get(0);

        PublishedStep publishedStep = new PublishedStep();

        Response response = pendingApprovalStep.approveRequest(requestId, publishedStep);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListsOfAppIdAreEqual(pendingApprovalStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID)
                , publishedStep.listOfPublishedAppId);

        assertThatListIsEmpty(pendingApprovalStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID));

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , publishedStep.listOfPublishedAppId);

        return requestId;

    }

    @Test
    public void checkIfTwoBatchesAreApproved() {

        String requestIdToDelete = checkIfOneBatchIsApproved();
        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_3, APPLICATION_ID_4);

        String requestNew = pendingApprovalStep.getRequestIdOutOfTwo(requestIdToDelete);

        PublishedStep publishedStep = new PublishedStep();

        Response response = pendingApprovalStep.approveRequest(requestNew, publishedStep);
        assertThatResponseCodeIsCorrect(response, 200);

        List<String> listOfApprovedAppId = pendingApprovalStep.addAppIdToList(publishedStep.listOfPublishedAppId, APPLICATION_ID_1, APPLICATION_ID_2);

        assertThatListsOfAppIdAreEqual(pendingApprovalStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID), listOfApprovedAppId);

        assertThatListIsEmpty(pendingApprovalStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID));

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , listOfApprovedAppId);

    }
}
