package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationUI extends MainPageObject {

    private static String
        MY_LIST_NAVIGATE_BUTTON = "//*[@content-desc = 'My lists']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_NAVIGATE_BUTTON),
                "Cannot find 'My lists' element",
                5);
    }
}
