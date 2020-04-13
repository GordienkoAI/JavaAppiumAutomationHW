package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String
        PLATFORM_IOS = "ios",
        PLATFORM_ANDROID = "android",
        AppiumURL = "http://127.0.0.1:4723/wd/hub";

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEny();
        driver = this.getPlatformDriver();

        this.rotateScreenPortrait();
    }



    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEny() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","9");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            //       capabilities.setCapability("appWaitActivity",".onboarding.InitialOnboardingActivity");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","C:\\Users\\gordienko_ani\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        } else if (platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "C:\\Users\\gordienko_ani\\Desktop\\JavaAppiumAutomation\\apks\\Wikipedia.app");

        } else {
            throw new Exception("Cannot get run platform from env variable. Platform values " + platform);
        }

        return capabilities;
    }

    private  AppiumDriver getPlatformDriver() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)){
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)){
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
        }
        else {
            throw new Exception("Cannot get run platform from env variable. Platform values " + platform);
        }
    }
}
