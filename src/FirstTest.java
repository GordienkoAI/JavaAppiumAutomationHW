import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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



    //--------------------------------Ex2: Создание метода ---------------------------------------------------------

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
    //--------------------------------------------------------------------------------------------------------------


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

    private boolean waitElementNotPresent(String id, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait =  new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
