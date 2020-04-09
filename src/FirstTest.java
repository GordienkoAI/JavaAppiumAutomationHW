import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {


    protected void setUp() throws Exception
    {
        super.setUp();
    }

    //===================================-Ex5: Тест: сохранение двух статей =======================================
    //------------------------------------Ex8: Рефакторинг тестов после рефакторинга------------------------------------------------------
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String searchText = "Ford",
                first_article = "Ford Mustang",
                second_article = "Ford GT",
                name_of_folder = "FordList";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();


//---------------------Добавление первого элемента-------------------------------------------
        SearchPageObject.clickByArticleWithSubstring(first_article);
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

//------------добавление второго элемента----------------------------------------------------

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.clickByArticleWithSubstring(second_article);
        ArticlePageObject.addNextArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

//------------------Удаление элемента из списка-----------------------------------------------
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.deleteArticleFromMyList(first_article);
    }

//==========================================================================================================
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programing language)",
                article_title
        );

    }

    //------------------------------------Ex6:  Тест: assert title----------------------------------------------------
    @Test
    public void testAssertTitle()
    {

        String searchText = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.waitForSearchResult("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        WebElement element = ArticlePageObject.waitForTitleElement();

        SearchPageObject.assertElementPresent(element);

    }
    //----------------------------------------------------------------------------------------------------------------
    @Test
    public void testSaveFirstArticleToMyList()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String searchText = "Java",
                first_article = "Java (programming language)",
                name_of_folder = "JavaList";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

//---------------------Добавление первого элемента-------------------------------------------
        SearchPageObject.clickByArticleWithSubstring(first_article);
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

//---------------------Удаление элемента-----------------------------------------------------

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.deleteArticleFromMyList(first_article);
    }


    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles(search_line);
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }


    @Test
    public void testAmountEmptySearch()
    {
        String search_line = "AASDGSDGDEAF";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch(search_line);
    }

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }


    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }


    @Test
    public void testChangeScreenOrientationOnScreen()
    {


        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();


        assertEquals(
                "Article title habe bin change after screen rotation ",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title habe bin change after screen rotation ",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void  testCheckSearchArticleInBackground()
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
