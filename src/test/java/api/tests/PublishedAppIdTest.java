package api.tests;

import api.steps.PublishedStep;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static constants.Constants.*;
import static api.steps.PublishedStep.generatePublishedAppId;

public class PublishedAppIdTest extends BaseTest {

    @Test
    public void checkIfSingleAppIdIsDeletedFromPublishedList() {
        PublishedStep publishedStep = generatePublishedAppId();
        Response response = publishedStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_PUBLISHED_LIST
                , publishedStep.listOfRemovedFromPublishedAppId
                , publishedStep.listOfPublishedAppId
                , APPLICATION_ID_1);

        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID)
                , publishedStep.listOfPublishedAppId);

        publishedStep.listOfPublishedAppId.addAll(publishedStep.listOfRemovedFromPublishedAppId);

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , publishedStep.listOfPublishedAppId);

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID)
                , publishedStep.listOfRemovedFromPublishedAppId);
    }

    @Test
    public void checkIfTwoAppIdAreDeletedFromPublishedList() {
        PublishedStep publishedStep = generatePublishedAppId();
        Response response = publishedStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_PUBLISHED_LIST
                , publishedStep.listOfRemovedFromPublishedAppId
                , publishedStep.listOfPublishedAppId
                , APPLICATION_ID_1
                , APPLICATION_ID_2);
        assertThatResponseCodeIsCorrect(response, 200);

        assertThatListIsEmpty(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID));

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_REJECTED_APP_ID)
                , publishedStep.listOfRemovedFromPublishedAppId);

        assertThatListsOfAppIdAreEqual(publishedStep.getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_LIVE_APP_ID)
                , publishedStep.listOfRemovedFromPublishedAppId);
    }
}
