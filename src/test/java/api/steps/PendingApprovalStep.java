package api.steps;

import api.tests.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.*;

public class PendingApprovalStep extends BaseStep {

    public List<String> listOfPendingApprovalAppId = new ArrayList<>();
    public List<String> listOfRemovedFromPendingApprovalAppId = new ArrayList<>();


    public List<String> getListOfPendingApprovalRequests() {
        Response response = sendGet(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_REQUESTS);
        List<List<String>> listOfListsOfPendingApprovalRequests = response.jsonPath().getList("$");
        return listOfListsOfPendingApprovalRequests.stream().map(e -> e.get(0)).collect(Collectors.toList());
    }

    public List<String> getListOfPendingApprovalAppIdPerRequest(String requestId) {
        return getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID + "&requestId="
                + requestId);
    }

    public List<String> addAppIdToList(List<String>listOfAppId, String... appIdsToAdd){
        for(String appId: appIdsToAdd){
            listOfAppId.add(appId);
        }
        return listOfAppId;
    }

    public String getRequestIdOutOfTwo(String requestIdToDelete){
        List<String> listOfRequests = getListOfPendingApprovalRequests();
        listOfRequests.remove(requestIdToDelete);
        return listOfRequests.get(0);
    }


    public static PendingApprovalStep generatePendingApprovalRequest(PendingApprovalStep pendingApprovalStep, String... appIds) {
        SubmitNewAppIdStep submitNewAppIdStep = new SubmitNewAppIdStep();
        submitNewAppIdStep.postAppIdToApprovalRequest(PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT, appIds);
        submitNewAppIdStep.approveAppId(pendingApprovalStep);
        return pendingApprovalStep;

    }

    public Response approveRequest(String requestId, PublishedStep publishedStep) {
        Response response = RestAssured.given()
                .header("Cache-Control", "no-cache")
                .contentType("multipart/form-data")
                .multiPart("requestId", requestId)
                .header(new Header("Authorization", BaseTest.authBearer))
                .post(PropertyReader.getUrl() + ENDPOINT_TO_APPROVE_PENDING_APPROVAL);
        publishedStep.listOfPublishedAppId = listOfPendingApprovalAppId;

        return response;
    }

}


