package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
        MY_LIST_NAVIGATE_BUTTON;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                MY_LIST_NAVIGATE_BUTTON,
                "Cannot find 'My lists' element",
                5);
    }
}
