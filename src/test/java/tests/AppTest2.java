package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

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
    Assert.assertNotNull(landingViewProductsText, "User successfully logged in!");
  }

  // @Test(priority = 2)
  public void checkProductDetails() {
    MobileElement validate = seeProductDetail(
        "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]",
        "//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");
    waitForSeconds(1);
    Assert.assertNotNull(validate, "Details not displayed.");
  }


  public void addProduct(final int position, final int swipes) {
    System.out.println("adding prod. " + position);
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView",
        true);
    super.getByXpath(
        // "(//android.view.ViewGroup[@content-desc=\"test-ADD TO
        // CART\"])[1]/android.widget.TextView",
        "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[" + position
            + "]/android.widget.TextView",
        true);
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup",
        true);
    // super.swipe(1);
    takeScreenshot("product added.");
    // super.getByXpath( "//android.view.ViewGroup[@content-desc=\"test-CONTINUE
    // SHOPPING\"]/android.widget.TextView", true);
    super.swipe(swipes);
    MobileElement el5 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView");
    el5.click();
    MobileElement el6 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]/android.widget.TextView");
    el6.click();
    super.waitForSeconds(2);
    System.out.println("back..");
  }

  @Test(priority = 3)
  public void addProducts() {
    System.out.println("adding products...");
    addProduct(2, 0);
    addProduct(1, 1);

//    super.swipe(2);
//    addProduct(2, 3);
//    addProduct(1, 4);

  }

  // @Test(priority = 3)
  public void addProductOne() {
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView",
        true);
    super.getByXpath(
        "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView",
        true);
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup",
        true);
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]/android.widget.TextView",
        true);
    super.waitForSeconds(2);
    takeScreenshot("product added.");
  }


  @Test(priority = 5)
  public void checkout() {    
    System.out.println("checkout...");
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView").click();
    //el38.click();
    System.out.println("shopping cart.");
    swipe(4);
    System.out.println("after swipes");
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]/android.widget.TextView").click();
    
    System.out.println("click checkout done");
    driver.findElementByAccessibilityId("test-First Name").sendKeys("Bastian");
    driver.findElementByAccessibilityId("test-Last Name").sendKeys("Bastias");
    driver.findElementByAccessibilityId("test-Zip/Postal Code").sendKeys("MN13W5");
    System.out.println("data added.");
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]/android.widget.TextView").click();
    System.out.println("continue done");
    swipe(4);
    System.out.println("after 2 swipes");
    super.takeScreenshot("checkOut init.");
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-FINISH\"]/android.widget.TextView").click();
    System.out.println("finishg done");
    super.takeScreenshot("checkOut finish.");
    super.takeScreenshot("finish.");
    super.waitForSeconds(3);
    driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]/android.widget.TextView").click();
  }



  public void testSwipe() {
    (new TouchAction(driver).press(500, 1800).perform().waitAction(200).moveTo(500, 900).perform())
        .release().perform();
    waitForSeconds(5);
    MobileElement one = (MobileElement) driver.findElementByXPath(
        "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[1]");
    one.click();
    System.out.println("click...");
    waitForSeconds(5);
  }

}
