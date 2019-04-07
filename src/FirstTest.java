import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","/Users/vitaly/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @Test
    public void firstTest(){

        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");

        element_to_init_search.click();

        WebElement element_to_init_search_line = waitForElementPresentByXpath("//*[contains(@text,'Search…')]", "Cannot find search input", 5);


                //driver.findElementByXPath("//*[contains(@text,'Search…')]");
        element_to_init_search_line.sendKeys("Appium");
       // System.out.println("First test run");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebElement waitForElementPresentByXpath(String xpath, String eroor_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(eroor_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
}
