package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {


    static {
        MY_LIST_NAVIGATE_BUTTON = "xpath://*[@content-desc = 'My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
