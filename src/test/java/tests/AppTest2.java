package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;

public class AppTest2 extends BaseTest {

  public AppTest2() {
    super(DEMO_2);
  }

  private By genericLogin(final String username, final String password,
      final String xpathToValidate) {
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"), username);
    setText(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"), password);
    // waitForSeconds(1);
    // driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();
    super.getByXpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]", true);
    waitForSeconds(2);
    By elementToValidate = By.xpath(xpathToValidate);
    waitForSeconds(1);
    takeScreenshot("login");
    return elementToValidate;
  }

  private void addProduct(final String productXpath) {
    // driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO
    // CART\"])[1]/android.widget.TextView").click();
    super.getByXpath(productXpath, true);
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup")
        .click();
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]/android.widget.TextView")
        .click();
    waitForSeconds(5);
    takeScreenshot("product added.");
  }

  private MobileElement seeProductDetail(final String productXpath, final String xpathToValidate) {
    // driver.findElementByXPath(id).click();
    super.getByXpath(productXpath, true);
    waitForSeconds(1);
    // driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-BACK TO
    // PRODUCTS\"]/android.widget.TextView").click();
    MobileElement validate = super.getByXpath(xpathToValidate, false);
    takeScreenshot("product details.");
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView",
        true);
    return validate;
  }

  // @Test
  public void incorrectLogin() {
    By message = genericLogin("fakeUser", "fakePassword",
        "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
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
    By landingViewProductsText = By.xpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView");
    // By landingViewProductsText =
    // login("standard_user","secret_sauces","//android.view.ViewGroup[@content-desc=\\\"test-Cart
    // drop zone\\\"]/android.view.ViewGroup/android.widget.TextView");
    Assert.assertNotNull(landingViewProductsText, "User successfully logged in!");
  }


  @Test(priority = 2)
  public void products() {
    MobileElement validate = seeProductDetail(
        "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]",
        "//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");
    waitForSeconds(1);
    Assert.assertNotNull(validate, "Details not displayed.");
    // driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop
    // zone\"]/android.view.ViewGroup/android.widget.TextView")).click();
    // driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO
    // CART\"])[1]/android.widget.TextView").click();
    // driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup").click();
    // driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-CONTINUE
    // SHOPPING\"]/android.widget.TextView").click();
    // waitForSeconds(5);
    // takeScreenshot("product added.");
  }



}
