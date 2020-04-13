package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@content-desc = 'Add this article to a reading list']",
        GO_IT_OPTIONS_BUTTON = "xpath://*[@text = 'GOT IT']",
        CREATE_NEW_OPTIONS_BUTTON = "xpath://*[@text = 'Create new']",
        NAME_OF_LIST_OPTIONS_FIELD = "xpath://*[@text = 'Name of this list']",
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/android.widget.ImageButton]";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindeElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article ",
                20
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find close article button", 5);
    }
    public void addFirstArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' element",
                5);

//        this.waitForElementAndClick(
//                By.xpath(GO_IT_OPTIONS_BUTTON),
//                "Cannot find 'GOT IT' button",
//                5);

        this.waitForElementAndClick(
                CREATE_NEW_OPTIONS_BUTTON,
                "Cannot find 'Create new' element1",
                10);

        this.waitForElementAndSendKeys(
                NAME_OF_LIST_OPTIONS_FIELD,
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                10);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find Button 'OK'",
                5);

    }

    public void addNextArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' element",
                5);

        this.waitForElementAndSendKeys(
                NAME_OF_LIST_OPTIONS_FIELD,
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find Button 'OK'",
                5);
    }

}
