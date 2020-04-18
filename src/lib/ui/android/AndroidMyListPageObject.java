package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_NAME_IN_MY_LIST_TPL = "xpath://*[@text = '{SUBSTRING}']";
    }

    public AndroidMyListPageObject (AppiumDriver driver){
        super(driver);
    }
}
