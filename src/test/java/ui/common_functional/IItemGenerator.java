package ui.common_functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ui.pages.BasePage.driver;

public interface IItemGenerator {

    <T> T generateItems(int quantity);


    static void assertThatItemsFitOnePage() {
        WebElement currentPage = driver.findElement(By.xpath("(//td[@colspan='2']/div/child::node())[3]"));
        assertThat(currentPage.getText().equals("1"))
                .as("Current page is NOT 1").isTrue();
    }

    static void assertThatItemsFitTwoPages() {
        WebElement parent = driver.findElement(By.xpath("//td[@colspan='2']/div"));

        String[] splitted = parent.getText().split(" of ");
        String totalPages = splitted[1];

        assertThat(totalPages.equals("2"))
                .as("Total number of Pages is NOT 2").isTrue();
    }
}

