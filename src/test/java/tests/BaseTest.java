package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

  protected final static int DEMO_1 = 1;
  protected final static int DEMO_2 = 2;
  //private String demoSelection;
  private DesiredCapabilities capabilities = new DesiredCapabilities();
  
  public BaseTest(int demoSelection) {
    super();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("platformVersion", "8.0.0");
    capabilities.setCapability("deviceName", "emulator-5554");
    switch (demoSelection) {
      case DEMO_1:
        capabilities.setCapability("appPackage", "com.kazimasum.cartdemo");
        capabilities.setCapability("appActivity", "MainActivity");
        break;
      case DEMO_2:        
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity", "MainActivity");
        break;

      default:
        break;
    }
  }

  AppiumDriver<MobileElement> driver;

  @BeforeSuite
  public void setUpAppium() throws MalformedURLException {

    final String URL_STRING = "http://127.0.0.1:4723/wd/hub";

    URL url = new URL(URL_STRING);

    //DesiredCapabilities capabilities = new DesiredCapabilities();
    // local
    /*
     * capabilities.setCapability("platformName", "Android");
     * capabilities.setCapability("platformVersion", "8.0.0");
     * capabilities.setCapability("deviceName", "emulator-5554");
     * capabilities.setCapability("appPackage", "com.kazimasum.cartdemo");
     * capabilities.setCapability("appActivity", "MainActivity");
     */
    //

    driver = new AndroidDriver<MobileElement>(url, capabilities);

    driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
  }

  @AfterSuite
  public void tearDownAppium() {
    driver.quit();
  }

  public void setText(By by, String text) {
    WebElement element = driver.findElement(by);
    element.clear();
    element.sendKeys(text);
    driver.hideKeyboard();
  }

  public boolean takeScreenshot(final String name) {
    String screenshotDirectory =
        System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
  }
  
  protected void waitForSeconds(int seconds) {
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
