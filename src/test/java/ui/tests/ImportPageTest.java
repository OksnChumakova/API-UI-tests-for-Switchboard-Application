package ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.ImportPage;

public class ImportPageTest extends BaseTest {

    private ImportPage importPage;

    @Override
    public void initPage() {
        importPage = new ImportPage();
    }

    @Override
    public void logout() {
        importPage.pressLogoutHeaderItem();
    }

    @BeforeMethod
    public void setUpMethod() {
        super.setUpMethod();
        importPage.setUrlForDriver();

    }

    // ChooseFile Button

    @Test
    public void checkIfChooseFileButtonIsPresentOnThePage() {
        importPage.assertThatChooseFileButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfChooseFileButtonIsEnabled() {
        importPage.assertThatChooseFileButtonIsEnabled();
    }

    // Upload Button

    @Test
    public void checkIfUploadButtonIsPresentOnThePage() {
        importPage.assertThatUploadButtonIsPresentOnThePage();
    }

    @Test
    public void checkIfUploadButtonContainsText() {
        importPage.assertThatUploadButtonContainsText();
    }

    @Test
    public void checkIfUploadButtonIsEnabled() {
        importPage.assertThatUploadButtonIsEnabled();
    }

    //

    @Test
    public void checkIfErrorMessageIsDisplayedAfterClickingUploadButtonWhenNoFileChosen(){
        importPage
                .pressUploadButton()
                .assertThatNoFilePartAlertIsDisplayed();
    }
}
