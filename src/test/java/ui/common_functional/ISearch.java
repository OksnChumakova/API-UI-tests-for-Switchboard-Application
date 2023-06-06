package ui.common_functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ui.pages.BasePage.driver;

public interface ISearch {

    void deleteItemFromSearchList();

    static String checkSearchResult() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table//tr/td"));
        if (listOfElements.size() != 0) {
            for (WebElement webElement : listOfElements) {
                if (webElement.getText() != " ") {
                    return webElement.getText();
                }
            }
        }
        return "";
    }

    static void assertThatSearchResultIsCorrect(String searchItemEntered) {
        enterValueIntoSearchField(searchItemEntered);
        String searchResultActual = checkSearchResult();

        assertThat(searchResultActual.toLowerCase().equals(searchItemEntered.toLowerCase()))
                .as("The item is NOT found").isTrue();

    }

    default void assertThatItemIsRemovedFromTheSearchListAfterBeingRemoved(String searchItemEntered) {
        assertThatSearchResultIsCorrect(searchItemEntered);
        deleteItemFromSearchList();

        List<WebElement> listOfElements= driver.findElements(By.xpath("//table[@class='table table-striped table-bordered table-hover']//td[text() != ' ']"));

        assertThat(listOfElements.size())
                .as("The item is still present in the search list").isEqualTo(0);

    }


    static void assertThatSearchResultIsNotFound(String searchItemEntered) {

        enterValueIntoSearchField(searchItemEntered);
        String searchResultActual = checkSearchResult();


        assertThat(searchResultActual.equals(""))
                .as("The item is found BUT! should be NOT").isTrue();

    }

    static void enterValueIntoSearchField(String searchValue) {
        WebElement searchField = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchField.sendKeys(searchValue);

    }

}
