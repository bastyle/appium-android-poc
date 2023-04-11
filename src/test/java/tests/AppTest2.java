package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest2 extends BaseTest {

  public AppTest2() {
    super(DEMO_2);
  }

  @Test
  public void login() {
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"),
        "standard_user");
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"), "secret_sauce");
    waitForSeconds(1);
    driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();
    waitForSeconds(3);
    takeScreenshot("login");
    By landingViewProductsText = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView");
    Assert.assertNotNull(landingViewProductsText, "Login incorrect!");
  }
  
  
  @Test
  public void products() {
    //driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")).click();
    driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView").click();
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup").click();
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]/android.widget.TextView").click();

    waitForSeconds(5);
    
  }



}
