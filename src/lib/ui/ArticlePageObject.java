package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        GO_IT_OPTIONS_BUTTON,
        CREATE_NEW_OPTIONS_BUTTON,
        NAME_OF_LIST_OPTIONS_FIELD,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON;


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
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else {
           return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindeElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article ",
                    20
            );
        }else{
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
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


    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot finde options button 'Add to my list'",
                5);

    }



}
