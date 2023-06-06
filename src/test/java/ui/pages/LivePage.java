package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LivePage extends BasePage {

    public LivePage() {
        super();
        pressLiveHeaderItem();
    }


    public LivePage assertThatApprovedAppIdIsOnLivePage(List<String> listOfAppId) {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//td"));
        compareTwoLists(listOfElements, listOfAppId);

        return this;

    }

    public LivePage assertThatNoAppIdIsDisplayedOnLivePage() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tbody/tr/td"));
        assertThat(listOfElements.size())
                .as("List Of App Id Is NOT Empty").isEqualTo(0);
        return this;

    }


}
