package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static String
            FOLDER_NAME_TPL = "//*[@text='{SUBSTRING}']",
            ARTICLE_NAME_IN_MY_LIST_TPL = "//*[@text = '{SUBSTRING}']";

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private String getXpathNameFolder(String substring)
    {
        return FOLDER_NAME_TPL.replace("{SUBSTRING}",substring);
    }

    private String getXpathArticleInFolder(String substring)
    {
        return ARTICLE_NAME_IN_MY_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    public void openFolderByName(String folder_name)
    {
        String name_of_folder_xpath = getXpathNameFolder(folder_name);
        this.waitForElementAndClick(
                By.xpath(name_of_folder_xpath),
                "Cannot find my list = '" + folder_name +"'",
                5);
    }

    public void deleteArticleFromMyList( String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String name_article_xpath = getXpathArticleInFolder(article_title);
        this.swipeElementToLeft(
                By.xpath(name_article_xpath),
                "Cannot find in list '"+ article_title+"' element");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String name_article_xpath = getXpathArticleInFolder(article_title);
        this.waitElementNotPresent(
                By.xpath(name_article_xpath),
                "This article '" + article_title + "' present in list",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
            String name_article_xpath = getXpathArticleInFolder(article_title);
            this.waitForElementPresent(
                    By.xpath(name_article_xpath),
                    "Cannot find save article'" + article_title + "' present in list",
                    5
            );
        }
}
