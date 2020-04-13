package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

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

}
