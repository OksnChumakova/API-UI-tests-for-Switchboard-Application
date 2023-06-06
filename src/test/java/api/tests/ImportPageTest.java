package api.tests;

import api.steps.ImportStep;
import api.steps.PublishedStep;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID;

public class ImportPageTest extends BaseTest {
    ImportStep importStep = new ImportStep();

    @Test
    public void checkIfCsvFileWithDistinctValuesIsUploaded() {
        Response response = importStep.uploadCsvFileWithDistinctAppId();
        assertThatResponseCodeIsCorrect(response, 200);

        List listOfAppIdFromPublishedPage = new PublishedStep().getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID);

        assertThatListsOfAppIdAreEqual(listOfAppIdFromPublishedPage, new ArrayList(importStep.setOfImportedAppIds));
        assertThatMessageInBodyAsTxtIsCorrect(response, "Imported applications: " + importStep.setOfImportedAppIds.size());

    }

    @Test
    public void checkIfCsvFileWithDuplicateValuesIsUploaded() {
        Response response = importStep.uploadCsvFileWithDuplicateAppId();
        assertThatResponseCodeIsCorrect(response, 200);

        List listOfAppIdFromPublishedPage = new PublishedStep().getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID);

        assertThatListsOfAppIdAreEqual(listOfAppIdFromPublishedPage, new ArrayList(importStep.setOfImportedAppIds));
        assertThatMessageInBodyAsTxtIsCorrect(response, "Imported applications: " + importStep.setOfImportedAppIds.size());

    }

    @Test
    public void checkIfErrorMessageIsCorrectInCaseSubmittingNoFile(){

        Response response = importStep.sendPostWithoutFile();
        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "No file part");

    }

    @Test
    public void checkIfErrorMessageIsCorrectInCasePngFileIsSent(){

        Response response = importStep.uploadPngFile();
        assertThatResponseCodeIsCorrect(response, 400);
        assertThatMessageInBodyAsTxtIsCorrect(response, "File is not supported.");
    }

    @Test
    public void checkIfNoAppIdIsUploadedAfterSameFileUpload() {
        Response response_1 = importStep.uploadCsvFileWithDistinctAppId();
        assertThatResponseCodeIsCorrect(response_1, 200);


        Response response_2 = importStep.uploadCsvFileWithDistinctAppId();
        assertThatResponseCodeIsCorrect(response_2, 200);

        List listOfAppIdFromPublishedPage = new PublishedStep().getAllAppIdFromThePage(PropertyReader.getUrl() + ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID);


        assertThatListsOfAppIdAreEqual(listOfAppIdFromPublishedPage, new ArrayList(importStep.setOfImportedAppIds));
        assertThatMessageInBodyAsTxtIsCorrect(response_2, "Imported applications: 0");

    }
}
