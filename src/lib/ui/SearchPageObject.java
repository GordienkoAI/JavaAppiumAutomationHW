package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[@text='Search…']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT_AMOUNT_TPL = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '{SUBSRING}']",
            EMPTY_RESULT_LABEL = "//*[@text = 'No results found']";

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
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result " + substring, 10);
    }

    public int getAmountOfFoundArticles(String search_line) {

        String search_result_locator = getSearchResultAmountEelement(search_line);
        this.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );

        return this.getAmountOfElements(By.xpath(search_result_locator));
     }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(By.xpath(EMPTY_RESULT_LABEL), "Cannot find empty result label", 10);
    }

    public void assertThereIsNoResultOfSearch(String search_line)
    {
        String search_result_locator = getSearchResultAmountEelement(search_line);
        this.assertElementNotPresent(By.xpath(search_result_locator), "We supposed not to find any results");
    }
}