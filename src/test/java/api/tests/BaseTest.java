package api.tests;

import api.steps.BaseStep;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constants.Constants.ENDPOINT_TO_LOGIN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BaseTest {
    BaseStep baseStep = new BaseStep();
    public static String authBearer = setSession();

    public static String setSession() {
        Map<String, String> authPayload = new HashMap<String, String>();
        authPayload.put("username", PropertyReader.getLogin());
        authPayload.put("password", PropertyReader.getPassword());

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(authPayload)
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );

        return "Bearer " + response.jsonPath().get("access_token");

    }

    @BeforeMethod
    public void cleanUp() {
        baseStep.deleteAppIdOnAllPages();
    }


    public void assertThatResponseCodeIsCorrect(Response response, int expectedResponseCode) {
        assertThat(response.getStatusCode() == expectedResponseCode)
                .as("Wrong response code. Actual: " + response.getStatusCode() + ". Expected: " + expectedResponseCode).isTrue();
    }

    public void assertThatResponseBodyIsCorrect(List<String> listOfActualAppId, List<String> listOfExpectedAppId) {
        assertThat(listOfActualAppId.equals(listOfExpectedAppId))
                .as("The list of Application ID is NOT correct").isTrue();
    }

    public void assertThatMessageInBodyAsTxtIsCorrect(Response response, String errorMessage) {
        String responseErrorMessage = response.asString().replaceAll("\"", "").trim();
        assertThat(responseErrorMessage.equals(errorMessage.trim()))
                .as("Error message is NOT correct. Actual: " + responseErrorMessage + ". Expected: " + errorMessage).isTrue();
    }

    public void assertThatErrorMessageInBodyAsJsonIsCorrect(Response response, String errorMessage) {
        String responseErrorMessage = response.jsonPath().getString("message");
        assertThat(responseErrorMessage.equals(errorMessage.trim()))
                .as("Error message is NOT correct. Actual: " + responseErrorMessage + ". Expected: " + errorMessage).isTrue();
    }

    public void assertThatResponseBodyIsNotEmpty(Response response) {
        assertThat(!response.getBody().asString().isEmpty())
                .as("Response BODY is empty").isTrue();
    }


    public void assertThatListsOfAppIdAreEqual(List<String> listOfActualAppId, List<String> listOfExpectedAppId) {
        List listOfActualAppIdModified = listOfActualAppId.stream().map(e -> e.trim().toLowerCase()).collect(Collectors.toList());

        Collections.sort(listOfActualAppIdModified);
        Collections.sort(listOfExpectedAppId);

        assertThat(listOfActualAppIdModified.equals(listOfExpectedAppId))
                .as("List Of App Id is NOT correct").isTrue();
    }


    public void assertThatListIsEmpty(List<String> listOfActualAppId) {
        assertThat(listOfActualAppId.isEmpty())
                .as("List of App Id is NOT empty").isTrue();
    }


}
