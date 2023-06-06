package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImportPage extends BasePage {

    @FindBy(xpath = "//input[@id='file']")
    WebElement chooseFileButton;

    @FindBy(xpath = "//button[text()='Upload']")
    WebElement uploadButton;

    @FindBy(xpath = "//div[@role='alert'][text()='No file part']")
    WebElement noFilePartAlert;



    // chooseFile Button

    public ImportPage pressChooseFileButton() {
        chooseFileButton.click();
        wait(2);
        return this;
    }


    public ImportPage assertThatChooseFileButtonIsPresentOnThePage() {
        assertThat(chooseFileButton.isDisplayed())
                .as("Choose File button element was not found").isTrue();
        return this;
    }

    public ImportPage assertThatChooseFileButtonIsEnabled() {
        assertThat(chooseFileButton.isEnabled())
                .as("Choose File Button is NOT enabled").isTrue();
        return this;
    }

    // uploadButton

    public ImportPage pressUploadButton() {
        uploadButton.click();
        wait(2);
        return this;
    }


    public ImportPage assertThatUploadButtonContainsText() {
        String text = "Upload";
        assertThat(uploadButton.getText())
                .as("Expected text " + text + " was not found").isEqualTo(text);
        return this;
    }


    public ImportPage assertThatUploadButtonIsPresentOnThePage() {
        assertThat(uploadButton.isDisplayed())
                .as("Upload button element was not found").isTrue();
        return this;
    }

    public ImportPage assertThatUploadButtonIsEnabled() {
        assertThat(uploadButton.isEnabled())
                .as("Upload Button is NOT enabled").isTrue();
        return this;
    }

    public void setUrlForDriver() {
        pressImportHeaderItem();
    }

    public ImportPage assertThatNoFilePartAlertIsDisplayed(){
        assertThat(noFilePartAlert.isDisplayed())
                .as("No File Part Alert element was not found").isTrue();
        return this;
    }
}
