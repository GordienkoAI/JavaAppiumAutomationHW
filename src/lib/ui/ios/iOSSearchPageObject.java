package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;


public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia'])";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_RESULT_ELEMENT_AMOUNT_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '{SUBSRING}']";
        EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name = 'No results found']";
    }

    public iOSSearchPageObject (AppiumDriver driver){
        super(driver);
    }

}
