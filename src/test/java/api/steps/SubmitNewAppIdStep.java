package api.steps;

import api.tests.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.ENDPOINT_TO_APPROVE;

public class SubmitNewAppIdStep extends BaseStep {

    public List<String> listOfAddedToApprovalRequestAppId = new ArrayList<>();
    public List<String> listOfRemovedFromApprovalRequestAppId = new ArrayList<>();


    public Response approveAppId(PendingApprovalStep pendingApprovalStep) {
        Response response = sendPost(PropertyReader.getUrl() + ENDPOINT_TO_APPROVE);
        pendingApprovalStep.listOfPendingApprovalAppId = listOfAddedToApprovalRequestAppId;
        return response;
    }

    public Response postAppIdToApprovalRequest(String url, String... appIds) {
        Response response = null;

        listOfAddedToApprovalRequestAppId = new ArrayList<>();

        for (String appId : appIds) {

            response = RestAssured.given()
                    .header("Cache-Control", "no-cache")
                    .contentType("multipart/form-data")
                    .multiPart("application", appId)
                    .header(new Header("Authorization", BaseTest.authBearer))
                    .post(url);

            listOfAddedToApprovalRequestAppId.add(appId);
        }

        return response;
    }


}


