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
    super.getByXpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]", true);
    waitForSeconds(2);
    By elementToValidate = By.xpath(xpathToValidate);
    waitForSeconds(1);
    takeScreenshot("login");
    return elementToValidate;
  }

  private MobileElement seeProductDetail(final String productXpath, final String xpathToValidate) {
    super.getByXpath(productXpath, true);
    waitForSeconds(1);
    MobileElement validate = super.getByXpath(xpathToValidate, false);
    takeScreenshot("product details.");
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView",
        true);
    return validate;
  }

  public MobileElement addProduct(final int position, final int swipes) {
    System.out.println("adding prod. " + position);
    super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView",
        true);
    super.getByXpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[" + position
        + "]/android.widget.TextView", true);
    MobileElement added= super.getByXpath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup",
        true);
    takeScreenshot("product added.");
    super.swipe(swipes);
    goToAllItems();
    return added;
  }

  private void goToAllItems() {
    MobileElement el5 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView");
    el5.click();
    MobileElement el6 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]/android.widget.TextView");
    el6.click();
    System.out.println("go to all items...");
    super.waitForSeconds(2);
  }

  @Test
  public void incorrectLogin() {
    By message = genericLogin("fakeUser", "fakePassword",
        "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    takeScreenshot("invalid login");
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

  @Test(priority = 2)
  public void checkProductDetails() {
    MobileElement validate = seeProductDetail(
        "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]",
        "//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");
    swipe(0);
    waitForSeconds(1);
    takeScreenshot("product details.");
    Assert.assertNotNull(validate, "Details not displayed.");
  }

  @Test(priority = 3)
  public void addProducts() {
    System.out.println("adding products...");
    Assert.assertNotNull(addProduct(2, 0),"Product not added");
    Assert.assertNotNull(addProduct(1, 1),"Product not added");
    super.swipe(2);
    Assert.assertNotNull(addProduct(2, 3),"Product not added");
    Assert.assertNotNull(addProduct(1, 4),"Product not added");
    
  }

  @Test(priority = 4)
  public void removeProduct() {
    System.out.println("removing product");
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup")
        .click();
    takeScreenshot("before remove");
    MobileElement el8 = (MobileElement) driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]/android.widget.TextView");
    el8.click();
    waitForSeconds(1);
    takeScreenshot("after remove");
    goToAllItems();
    Assert.assertNotNull(el8);
  }

  @Test(priority = 5)
  public void checkout() {
    System.out.println("checkout...");
    driver.findElementByXPath(
        "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
        .click();
    swipe(4);
    driver
        .findElementByXPath(
            "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]/android.widget.TextView")
        .click();
    driver.findElementByAccessibilityId("test-First Name").sendKeys("Bastian");
    driver.findElementByAccessibilityId("test-Last Name").sendKeys("Bastias");
    driver.findElementByAccessibilityId("test-Zip/Postal Code").sendKeys("MN13W5");
    driver
        .findElementByXPath(
            "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]/android.widget.TextView")
        .click();
    swipe(4);
    super.takeScreenshot("checkOut init.");
    driver
        .findElementByXPath(
            "//android.view.ViewGroup[@content-desc=\"test-FINISH\"]/android.widget.TextView")
        .click();
    super.takeScreenshot("checkOut finish.");
    super.takeScreenshot("finish.");
    super.waitForSeconds(3);
    //driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]/android.widget.TextView").click();
    Assert.assertNotNull(super.getByXpath("//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]/android.widget.TextView", true));    
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
