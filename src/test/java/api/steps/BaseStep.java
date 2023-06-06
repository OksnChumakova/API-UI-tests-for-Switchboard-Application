package api.steps;

import api.tests.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utils.PropertyReader;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.*;

public class BaseStep {

    public Response sendPost(String url) {

        Response response = RestAssured.given()
                .header("Cache-Control", "no-cache")
                .contentType("application/json")
                .header(new Header("Authorization", BaseTest.authBearer))
                .post(url);
        return response;
    }

    public Response postCsvFile(File file, String url) {

        return RestAssured.given()
                .header("Cache-Control", "no-cache")
                .contentType("multipart/form-data")
                .multiPart("file", file, "application/vnd.ms-excel")
                .header(new Header("Authorization", BaseTest.authBearer))
                .post(url);

    }


    public Response sendGet(String url) {
        Response response = RestAssured.given()
                .header(new Header("Authorization", BaseTest.authBearer))
                .get(url);

        return response;
    }

    public Response sendGetWithoutAuthorization(String url) {
        Response response = RestAssured.given()
                .get(url);

        return response;
    }


    public Response sendDelete(String url, List<String> listOfAppIdToBeDeleted) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(listOfAppIdToBeDeleted)
                .header(new Header("Authorization", BaseTest.authBearer))
                .delete(url);
        return response;
    }

    public Response sendDelete(String url) {
        Response response = RestAssured.given()
                .header(new Header("Authorization", BaseTest.authBearer))
                .delete(url);
        return response;
    }

    public List<String> getAllAppIdFromThePage(String url) {
        Response response = sendGet(url);

        List<String> listOfAppIdDisplayedOnThePage = response.jsonPath().getList("$");
        return listOfAppIdDisplayedOnThePage;
    }


    public Response deleteAppIdFromList(String endpoint, List<String> listOfAppIdToBeDeleted, List<String> listOfAppIdNotDeleted, String... appIds) {
        for (String appId : appIds) {
            listOfAppIdToBeDeleted.add(appId);
            listOfAppIdNotDeleted.remove(appId);
        }
        return sendDelete(PropertyReader.getUrl() + endpoint, listOfAppIdToBeDeleted);

    }


    public void deleteAppIdOnAllPages() {

        //remove from submit new

        getListOfAppIdAndDelete(PropertyReader.getUrl() + ENDPOINT_TO_GET_SUBMITTED_APP_ID
                , PropertyReader.getUrl() + ENDPOINT_TO_DELETE_APP_ID_FROM_REQUEST_FOR_APPROVAL);

        // remove from pending approval
        Response response = sendGet(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_REQUESTS);
        List<List<String>> listOfListsOfPendingApprovalRequests = response.jsonPath().getList("$");

        if (listOfListsOfPendingApprovalRequests.size() != 0) {
            List<String> listOfPendingApprovalRequest = listOfListsOfPendingApprovalRequests.stream().map(e -> e.get(0)).collect(Collectors.toList());

            for (String requestForApproval : listOfPendingApprovalRequest) {

                getListOfAppIdAndDelete(PropertyReader.getUrl() + ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID + "&requestId=" + requestForApproval
                        , PropertyReader.getUrl() + ENDPOINT_TO_DELETE_APP_ID_FROM_PENDING_APPROVAL_LIST);
            }
        }

        // remove from published

        getListOfAppIdAndDelete(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID
                , PropertyReader.getUrl() + ENDPOINT_TO_DELETE_APP_ID_FROM_PUBLISHED_LIST);

        //remove rejected
        getListOfAppIdAndDelete(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID
                , PropertyReader.getUrl() + SUBMIT_APP_ID_END_POINT);
    }


    public void getListOfAppIdAndDelete(String urlToGetListOfAppIdOnThePage, String urlToDeleteAppId) {
        List<String> listOfAppId = getAllAppIdFromThePage(urlToGetListOfAppIdOnThePage);
        if (listOfAppId.size() != 0) {
            sendDelete(urlToDeleteAppId, listOfAppId);
        }
    }


}
