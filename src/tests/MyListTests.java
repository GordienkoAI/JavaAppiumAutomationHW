package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String
            searchText = "Java",
            first_article = "Java (programming language)",
            name_of_folder = "JavaList";

    @Test
    public void testSaveFirstArticleToMyList()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        SearchPageObject.clickByArticleWithSubstring(first_article);

//---------------------Добавление первого элемента-------------------------------------------

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

//---------------------Удаление элемента-----------------------------------------------------

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.deleteArticleFromMyList(first_article);
    }


    //===================================-Ex5: Тест: сохранение двух статей =======================================
    //------------------------------------Ex8: Рефакторинг тестов после рефакторинга------------------------------------------------------
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String  searchText = "Ford",
                first_article = "Ford Mustang",
                second_article = "Ford GT",
                name_of_folder = "FordList";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        SearchPageObject.clickByArticleWithSubstring(first_article);

    //---------------------Добавление первого элемента-------------------------------------------
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

    //------------добавление второго элемента----------------------------------------------------

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.clickByArticleWithSubstring(second_article);


        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addNextArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

    //------------------Удаление элемента из списка-----------------------------------------------
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.deleteArticleFromMyList(first_article);

      //  Не выходя на главный экран, можно в существующем списке сделать поиск статьи которую ожидаем увидеть
        SearchPageObject.waitForSearchResult(second_article);
    }

}
