package api.steps;

import api.tests.BaseTest;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utils.PropertyReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static constants.Constants.ENDPOINT_TO_IMPORT_CSV_FILE;

public class ImportStep extends BaseStep {

    public static final String CSV_FILE_WITH_DISTINCT_APP_IDS = "src/test/resources/Distinct_app_name_and_version.csv";
    public static final String CSV_FILE_WITH_DUPLICATE_APP_IDS = "src/test/resources/Duplicate_app_name_and_version.csv";
    public static final String PNG_FILE = "src/test/resources/logoPluto.png";
    public Set<String> setOfImportedAppIds = new HashSet();

    public Response uploadCsvFileWithDistinctAppId() {
        readCsvFile(CSV_FILE_WITH_DISTINCT_APP_IDS);

        return postCsvFile(new File(CSV_FILE_WITH_DISTINCT_APP_IDS), PropertyReader.getUrl() + ENDPOINT_TO_IMPORT_CSV_FILE);
    }

    public Response uploadCsvFileWithDuplicateAppId() {
        readCsvFile(CSV_FILE_WITH_DUPLICATE_APP_IDS);

        return postCsvFile(new File(CSV_FILE_WITH_DUPLICATE_APP_IDS), PropertyReader.getUrl() + ENDPOINT_TO_IMPORT_CSV_FILE);
    }

    public Response uploadPngFile(){
        return postCsvFile(new File(PNG_FILE), PropertyReader.getUrl() + ENDPOINT_TO_IMPORT_CSV_FILE);
    }

    public Response sendPostWithoutFile() {

        return RestAssured.given()
                .header("Cache-Control", "no-cache")
                .contentType("multipart/form-data")
                .header(new Header("Authorization", BaseTest.authBearer))
//                .log().all()
                .post(PropertyReader.getUrl() + ENDPOINT_TO_IMPORT_CSV_FILE);
    }

    private void readCsvFile(String file) {
        setOfImportedAppIds = new HashSet<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] lineInArray = reader.readNext();

            for (int i = 0; lineInArray != null; i++) {

                if (i == 0 || lineInArray[0].equals("")) {
                    lineInArray = reader.readNext();

                } else {
                    setOfImportedAppIds.add(lineInArray[0].trim().toLowerCase() + "@" + lineInArray[1].trim().toLowerCase());
                    lineInArray = reader.readNext();
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }


}
