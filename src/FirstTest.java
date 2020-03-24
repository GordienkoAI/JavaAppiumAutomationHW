import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appWaitActivity",".onboarding.InitialOnboardingActivity");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\gordienko_ani\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebElement button = driver.findElementByXPath("//android.widget.TextView[@text = 'SKIP']");
        button.click();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    //-----------------------------------Ex5: Тест: сохранение двух статей--------------------------------------------
    @Test
    public void saveTwoArticleToMyList()
    {
         waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String searchText = "Ford";
        inputSearchText(searchText);

        String first_article = "Ford Mustang";
        waitForElementAndClick(
                By.xpath("//*[@text = '" + first_article + "']"),
                "Cannot find Ford Mustang article",
                5);


        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Add this article to a reading list']"),
                "Cannot find 'Add this article to a reading list' element",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'GOT IT']"),
                "Cannot find 'GOT IT' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Create new']"),
                "Cannot find 'Create new' element",
                5);

        String name_of_folder = "FordList";

        waitForElementAndSendKeys(
                By.xpath("//*[@text = 'Name of this list']"),
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find Button 'OK'",
                5);


        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Cannot find 'Navigate up' element",
                5);

//------------добавление второго элемента----------------------------------------------------

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        inputSearchText(searchText);

        String second_article = "Ford GT";
        waitForElementAndClick(
                By.xpath("//*[@text = '" + second_article + "']"),
                "Cannot find Ford Mustang article",
                5);


        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Add this article to a reading list']"),
                "Cannot find 'Add this article to a reading list' element",
                5);


        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder +"']"),
                "Cannot find 'Create new' element",
                10);

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Cannot find 'Navigate up' element",
                5);

//------------------Удаление элемента из списка-----------------------------------------------

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'My lists']"),
                "Cannot find 'My lists' element",
                5);


        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find my list = '" + name_of_folder +"'",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text = '" +  first_article + "']"),
                "Cannot find in list 'Java (programming language)' element");


        waitForElementPresent(
                By.xpath("//*[@text= '"+ second_article +"']"),
                "Second article "+ second_article + " not found in My list",
                5
        );
    }

    //-----------------------------------Ex5: Тест: сохранение двух статей--------------------------------------------
    @Test
    public void compareSrticleTitle()
    {
        WebElement search_element =   waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String searchText = "Java";
        WebElement searchText_element = inputSearchText(searchText);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        List<WebElement> webElementList =  driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup"));

//        List<WebElement> webElementsList = waitForListElements(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup"),
//                "Can not find elements",
//                10
//        );

        int count_list = webElementList.size();
        Assert.assertTrue("Search count < 1", count_list > 1);

        for (int i = 0; i < count_list; i++)
        {
            WebElement inner_element  = webElementList.get(i).findElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
            Assert.assertTrue("Element " + i +" not contains "+ searchText, inner_element.getText().contains(searchText));
        }

        searchText_element.clear();
        boolean searchClear = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup")).isEmpty();
        Assert.assertTrue("Element List not empty", searchClear);

    }


    @Test
    public void saveFirstArticleToMyList()
    {
        WebElement search_element =   waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String searchText = "Java";
        WebElement searchText_element = inputSearchText(searchText);

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' in Java search",
                50);


        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Add this article to a reading list']"),
                "Cannot find 'Add this article to a reading list' element",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'GOT IT']"),
                "Cannot find 'GOT IT' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Create new']"),
                "Cannot find 'Create new' element",
                10);

        String name_of_folder = "JavaList";

        waitForElementAndSendKeys(
                By.xpath("//*[@text = 'Name of this list']"),
                name_of_folder,
                "Cannot find 'Text input in My List' element",
                5);


        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find Button 'OK'",
                5);

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Cannot find 'Navigate up' element",
                5);

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'My lists']"),
                "Cannot find 'My lists' element",
                5);


        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find my list = '" + name_of_folder +"'",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find in list 'Java (programming language)' element");

        waitElementNotPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot deleted article",
                5
        );
    }


    @Test
    public void testAmountOfNotEmptySearch()
    {
        WebElement search_element =   waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

           String search_line = "Linkin Park Discography";
//        waitForElementAndSendKeys(
//                By.xpath("//*[@text = '" +search_line+ "']"),
//                search_line,
//                "Cannot find search input",
//                5
//        );

        inputSearchText(search_line);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '"+ search_line +"']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_result_locator,
                15
        );


        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);


    }


    @Test
    public void testAmountEmptySearch()
    {

            WebElement search_element =   waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                    "Cannot find search first input element",
                    5);

            String search_line = "Java";
            inputSearchText(search_line);

            String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@text = '"+ search_line +"']";
            String empty_sresult_label = "//*[@text = 'No results found']";

            waitForElementPresent(
                    By.xpath(empty_sresult_label),
                    "Cannot find empty result label by the request " + empty_sresult_label,
                    5
            );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
        }


    @Test
    public void testSwipeArticle()
    {

        WebElement search_element =   waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String searchText = "Appium";
        WebElement searchText_element = inputSearchText(searchText);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot element for title = 'Appium'",
                15
        );



        swipeUpToFindeElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end element of article",
                20
        );

    }


    @Test
    public void testChangeScreenOrientationOnScreen()
    {

        WebElement search_element = waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String search_line = "Java";
        inputSearchText(search_line);

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' in Java search",
                15);


        String title_before_rotation = waitElementAndGetAttribute(
               By.xpath("//*android.view.View[@text='Java (programming language)']"),
            "text",
            "Can not find title of article",
            15
    );

          driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitElementAndGetAttribute(
                By.xpath("//*android.view.View[@text='Java (programming language)']"),
            "text",
            "Can not find title of article",
            15
    );


        Assert.assertEquals(
                "Article title habe bin change after screen rotation ",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitElementAndGetAttribute(
                By.xpath("//*android.view.View[@text='Java (programming language)']"),
                "text",
                "Can not find title of article",
                15
        );

        Assert.assertEquals(
                "Article title habe bin change after screen rotation ",
                title_before_rotation,
                title_after_second_rotation
        );

    }

    @Test
    public void  testCheckSearchArticleInBackground()
    {
        WebElement search_element =   waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search first input element",
                5);

        String searchText = "Java";
        WebElement searchText_element = inputSearchText(searchText);

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' in Java search",
                5);

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']"),
                "Cannot find article after returning background",
                5);
    }


    //-------------------------------- Методы класса ---------------------------------------------------------

    protected void swipeWindow(int timeOfSeconds)
    {
            TouchAction action = new TouchAction(driver);

            Dimension size = driver.manage().window().getSize();

            int x = (int) (size.width / 2);
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action.moveTo(x, start_y).waitAction(timeOfSeconds).moveTo(x, end_y).perform();
        }

    private WebElement inputSearchText(String Keys)
    {

        WebElement element = waitForElementPresent(
                By.xpath("//android.widget.EditText[@text='Search Wikipedia']"),
                "Cannot find 'Search Wikipedia' line",
                5);

        String find_element = element.getText();

        Assert.assertEquals(
                "Cannot find Search Wikipedia",
                "Search Wikipedia",
                find_element
        );

        element.sendKeys(Keys);
        return element;

    }

    private List<WebElement> waitForListElements(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List<WebElement> webElementList =  driver.findElements(by);
        return webElementList;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait =  new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String Keys, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent( by,  error_message,  timeoutInSeconds);
        element.sendKeys(Keys);
        return element;
    }

    private boolean waitElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait =  new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width /2;
        int start_y =(int) (size.height * 0.8);
        int end_y =(int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindeElement(By by, String error_messgae, int max_swipes)
    {
        int already_swiped = 0;

        while(driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_messgae, 10);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        };
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().width;

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().height;
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by)
    {
        List element = driver.findElements(by);
        return element.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSecond)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        return element.getAttribute(attribute);
    }
}
