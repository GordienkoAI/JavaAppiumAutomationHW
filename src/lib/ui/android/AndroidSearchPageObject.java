package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
            SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Search Wikipedia']";
            SEARCH_INPUT = "xpath://*[@text='Search…']";
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
            SEARCH_RESULT_ELEMENT_AMOUNT_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '{SUBSRING}']";
            EMPTY_RESULT_LABEL = "xpath://*[@text = 'No results found']";
        }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
