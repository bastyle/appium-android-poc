package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest2 extends BaseTest {

  public AppTest2() {
    super(DEMO_2);
  }

  private By login(final String username,final  String password,final  String xpathToValidate) {
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"),
        username);
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"), password);
    //waitForSeconds(1);
    driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();
    waitForSeconds(2);
    By elementToValidate = By.xpath(xpathToValidate);
    waitForSeconds(1);
    takeScreenshot("login");
    return elementToValidate;
  }
  
  @Test
  public void incorrectLogin() {
    By message = login("fakeUser", "fakePassword", "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    Assert.assertNotNull(message, "Login incorrect!");
  }
  
  @Test(priority = 1)
  public void login() {
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"),
        "standard_user");
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"), "secret_sauce");
    waitForSeconds(1);
    driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();
    waitForSeconds(3);
    takeScreenshot("login");
    By landingViewProductsText = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView");
    //By landingViewProductsText = login("standard_user","secret_sauces","//android.view.ViewGroup[@content-desc=\\\"test-Cart drop zone\\\"]/android.view.ViewGroup/android.widget.TextView");
    Assert.assertNotNull(landingViewProductsText, "User successfully logged in!");
  }
  
  
  @Test(priority = 2)
  public void products() {
    //driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")).click();
    driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView").click();
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup").click();
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]/android.widget.TextView").click();

    waitForSeconds(5);
    
  }



}
