package api.tests;

import api.steps.LoginStep;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static constants.Constants.*;

public class LoginAndLogoutTest extends BaseTest {

    LoginStep loginStep = new LoginStep();


    @Test
    public void checkIfLoginIsSuccessfulAfterProvidingValidCredentials() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginStep.addValidCredentials())
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );
        assertThatResponseCodeIsCorrect(response, 200);
        assertThatResponseBodyIsNotEmpty(response);
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingNoCredentials() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginStep.addEmptyCredentials())
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );
        assertThatResponseCodeIsCorrect(response, 401);
        assertThatErrorMessageInBodyAsJsonIsCorrect(response, "The username and/or password are incorrect");
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingInvalidLogin() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginStep.addInvalidLogin())
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );
        assertThatResponseCodeIsCorrect(response, 401);
        assertThatErrorMessageInBodyAsJsonIsCorrect(response, "The username and/or password are incorrect");
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingInvalidPassword() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginStep.addInvalidPassword())
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );
        assertThatResponseCodeIsCorrect(response, 401);
        assertThatErrorMessageInBodyAsJsonIsCorrect(response, "The username and/or password are incorrect");
    }

    @Test
    public void checkIfLoginIsFailedAfterProvidingInvalidCredentials() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(loginStep.addInvalidCredentials())
                .post(PropertyReader.getUrl() + ENDPOINT_TO_LOGIN
                );
        assertThatResponseCodeIsCorrect(response, 401);
        assertThatErrorMessageInBodyAsJsonIsCorrect(response, "The username and/or password are incorrect");
    }

    @Test
    public void checkIfLogoutWorks() {

        Response response_1 = RestAssured.given()
                .get(PropertyReader.getUrl() + ENDPOINT_TO_LOGOUT);
        assertThatResponseCodeIsCorrect(response_1, 200);

        Response response_2 = loginStep.sendGetWithoutAuthorization(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID);
        assertThatResponseCodeIsCorrect(response_2, 401);
    }

}
