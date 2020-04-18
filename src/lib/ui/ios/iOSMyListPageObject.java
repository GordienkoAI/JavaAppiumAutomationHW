package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_NAME_IN_MY_LIST_TPL = "xpath://XCUIElementTypeLink[contains(@name = '{SUBSTRING}')]";
    }

    public iOSMyListPageObject (AppiumDriver driver){
        super(driver);
    }
}
