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

  // @Test(priority = 2)
  public void checkProductDetails() {
    MobileElement validate = seeProductDetail(
        "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]",
        "//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");
    waitForSeconds(1);
    Assert.assertNotNull(validate, "Details not displayed.");
  }


 @Test(priority = 3)
  public void addProductOne() {
    // driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop
    // zone\"]/android.view.ViewGroup/android.widget.TextView")).click();
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView",
        true);
    // driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO
    // CART\"])[1]/android.widget.TextView").click();
    super.getByXpath(
        "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView",
        true);
    // driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup").click();
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup",
        true);
    // driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-CONTINUE
    // SHOPPING\"]/android.widget.TextView").click();
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"]/android.widget.TextView",
        true);
    waitForSeconds(5);
    // takeScreenshot("product added.");
  }


 @Test(priority = 5)
  public void checkout() {
    MobileElement el38 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
    el38.click();
    MobileElement el39 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]/android.widget.TextView");
    el39.click();
    MobileElement el40 = (MobileElement) driver.findElementByAccessibilityId("test-First Name");
    el40.sendKeys("Bastian");
    MobileElement el41 = (MobileElement) driver.findElementByAccessibilityId("test-Last Name");
    el41.sendKeys("Bastias");
    MobileElement el42 =
        (MobileElement) driver.findElementByAccessibilityId("test-Zip/Postal Code");
    el42.sendKeys("MN13W5");
    MobileElement el43 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]/android.widget.TextView");
    el43.click();
   
    swipe(2);


    MobileElement el44 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-FINISH\"]/android.widget.TextView");
    el44.click();
    MobileElement el45 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]/android.widget.TextView");
    el45.click();
  }

  protected void swipe(final int times) {
    int countAux = times;
    do {
      (new TouchAction(driver).press(500, 1800).perform().waitAction(200).moveTo(500, 900)
          .perform()).release().perform();
      countAux--;
    } while (countAux > 0);
    waitForSeconds(2);
  }

  @Test(priority = 2)
  public void testSwipe() {
    // waitForSeconds(5);
    // System.out.println(111);
    // driver.swipe(300, 1500, 300, 710, 1);
    // waitForSeconds(5);
    // System.out.println(222);
    // MobileElement one = (MobileElement)
    // driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[1]");
    // MobileElement two = (MobileElement) driver.findElementByXPath(
    // "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[3]/android.view.ViewGroup/android.widget.ImageView");
    // (new
    // TouchAction(driver).press(two).waitAction(500).moveTo(one).waitAction(500)).release().perform();
    // (new
    // TouchAction(driver).press(two).waitAction(1000).perform().waitAction(500).moveTo(one).waitAction(500).release().waitAction(200)).perform();
    // (new
    // TouchAction(driver).press(two).waitAction(1000).perform()).moveTo(one).waitAction(500).perform().release().waitAction(200);
    (new TouchAction(driver).press(500, 1800).perform().waitAction(200).moveTo(500, 900).perform())
        .release().perform();
    waitForSeconds(5);
    MobileElement one = (MobileElement) driver.findElementByXPath(
        "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[1]");
    one.click();
    System.out.println("click...");
    waitForSeconds(5);
    // driver.sw
    // waitForSeconds(5);
    // System.out.println(333);
    // waitForSeconds(5);
    //
    // org.openqa.selenium.Point centerOfElement = two.getCenter();
    // new TouchAction(driver).tap(point(centerOfElement.x, centerOfElement.y)).perform();

    // MobileElement test =
    // driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[1]");
    // test.click();

  }

}
