package api.tests;

import api.steps.RejectedStep;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static constants.Constants.*;
import static api.steps.RejectedStep.generateRejectedAppId;

public class PendingRejectionTest extends BaseTest{

    @Test
    public void checkIfOneAppIdIsDeletedFromRejectionList(){
        RejectedStep rejectedStep = generateRejectedAppId();

        Response response = rejectedStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_REJECTION_LIST
                , rejectedStep.listOfAppIdRemovedFromRejectedList
                , rejectedStep.listOfRejectedAppId
                , APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 200);


        assertThatListsOfAppIdAreEqual(rejectedStep.getAllAppIdFromThePage(utils.PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID)
                , rejectedStep.listOfRejectedAppId);

        assertThatListsOfAppIdAreEqual(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID)
                , rejectedStep.listOfAppIdRemovedFromRejectedList);

        rejectedStep.listOfAppIdRemovedFromRejectedList.addAll(rejectedStep.listOfRejectedAppId);
        assertThatListsOfAppIdAreEqual(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , rejectedStep.listOfAppIdRemovedFromRejectedList);

    }

    @Test
    public void checkTwoAppIdAreDeletedFromRejectionList(){
        RejectedStep rejectedStep = generateRejectedAppId();

        Response response = rejectedStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_REJECTION_LIST
                , rejectedStep.listOfAppIdRemovedFromRejectedList
                , rejectedStep.listOfRejectedAppId
                , APPLICATION_ID_1
                , APPLICATION_ID_2);

        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID));


        assertThatListsOfAppIdAreEqual(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID)
                , rejectedStep.listOfAppIdRemovedFromRejectedList);

        assertThatListsOfAppIdAreEqual(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , rejectedStep.listOfAppIdRemovedFromRejectedList);
    }

    @Test
    public void checkIfAppIdAreRejected(){
        RejectedStep rejectedStep = generateRejectedAppId();

        Response response = rejectedStep.sendDelete(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID));
        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID));
        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID));
        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID));
        assertThatListIsEmpty(rejectedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID));

    }
}
