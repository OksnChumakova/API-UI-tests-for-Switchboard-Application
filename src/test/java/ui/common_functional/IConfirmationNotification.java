package ui.common_functional;

import org.openqa.selenium.By;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ui.pages.BasePage.driver;

public interface IConfirmationNotification {

    static void assertThatConfirmationNotificationShowsUp(String notificationMessage) {
        String alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']")).getText();
        assertThat(alertMessage)
                .as("Alert Message is Wrong").isEqualTo(notificationMessage);
    }
}
