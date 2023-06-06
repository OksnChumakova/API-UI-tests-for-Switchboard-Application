package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.common_functional.ISearch;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PublishedPage extends BasePage implements ISearch {


    List<String> listOfRejectedAppIds = new ArrayList<>();
    List<String> listOfApprovedAppIds = new ArrayList<>();


    public PublishedPage() {
        super();
        pressPublishedHeaderItem();
    }

    public List<String> getListOfRejectedAppIds() {
        return listOfRejectedAppIds;

    }



    public void setListOfApprovedAppIds(List<String> listOfApprovedAppIds) {
        this.listOfApprovedAppIds = listOfApprovedAppIds;
    }

    public PublishedPage assertThatApprovedAppIdIsOnPublishedPage(List<String> listOfAppId) {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr/td"));
        compareTwoLists(listOfElements, listOfAppId);

        return this;

    }

    public PublishedPage assertThatNoAppIdIsDisplayedOnPublishedPage() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr/td"));

        assertThat(listOfElements.size())
                .as("List Of App Id Is NOT Empty").isEqualTo(0);
        return this;

    }

    public void setUrlForDriver() {
        pressPublishedHeaderItem();
    }

    public PublishedPage removeOnePublishedAppId(PendingApprovalPage pendingApprovalPage) {
        listOfRejectedAppIds = new ArrayList<>();
        String removedAppId = clickOneCheckBoxOnThePageAndGetItsId();
        listOfRejectedAppIds.add(removedAppId);
        pendingApprovalPage.listOfAppIdInApprovalRequest.remove(removedAppId);
        pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp();
        return this;
    }

    public PublishedPage removeAllPublishedAppId(PendingApprovalPage pendingApprovalPage) {
        clickCheckAllCheckbox()
                .pressRemoveButton()
                .pressRemoveButtonOnRemovalConfirmationPopUp();

        listOfRejectedAppIds = pendingApprovalPage.listOfAppIdInApprovalRequest;
        return this;
    }

    public void deleteItemFromSearchList(){
        driver.findElement(By.xpath("(//div[@class='form-check form-check-inline']//input[@type='checkbox'])[1]")).click();
        pressRemoveButton();
        pressRemoveButtonOnRemovalConfirmationPopUp();
        wait(4);
    }


}
