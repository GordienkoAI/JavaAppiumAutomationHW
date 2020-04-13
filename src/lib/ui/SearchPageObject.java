package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text='Search Wikipedia']",
            SEARCH_INPUT = "xpath://*[@text='Search…']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT_AMOUNT_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '{SUBSRING}']",
            EMPTY_RESULT_LABEL = "xpath://*[@text = 'No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultAmountEelement(String search_line)
    {
        return SEARCH_RESULT_ELEMENT_AMOUNT_TPL.replace("{SUBSRING}",search_line);
    }
    /* TEMPLATE METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result " + substring, 10);
    }

    public int getAmountOfFoundArticles(String search_line) {

        String search_result_locator = getSearchResultAmountEelement(search_line);
        this.waitForElementPresent(
                search_result_locator,
                "Cannot find anything by the request " + search_line,
                15
        );

        return this.getAmountOfElements(search_result_locator);
     }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(EMPTY_RESULT_LABEL, "Cannot find empty result label", 10);
    }

    public void assertThereIsNoResultOfSearch(String search_line)
    {
        String search_result_locator = getSearchResultAmountEelement(search_line);
        this.assertElementNotPresent(search_result_locator, "We supposed not to find any results");
    }
}
