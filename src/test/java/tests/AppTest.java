package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void enterData(int id, String name, int price, int quantity) {
        setText(By.id("t1"), String.valueOf(id));
        setText(By.id("t2"), name);
        setText(By.id("t3"), String.valueOf(price));
        setText(By.id("t4"), String.valueOf(quantity));
        
        driver.findElement(By.id("b1")).click();
        
        final String producAddedMsgExpected = "Product Inserted Successfully";
        String status = driver.findElement(By.id("lbl")).getText();
        System.out.println("status>> "+status);
        takeScreenshot(producAddedMsgExpected);
        Assert.assertEquals(status, producAddedMsgExpected, "Product not added!");
        //Assert.assertEquals(true,true);
    }
    
    @Test(priority = 1)
    public void checkShoppingCart() {
      driver.findElement(By.id("b2")).click();
      System.out.println("checkShoppingCart...");
      try {
        Thread.sleep(12000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[1][4];
        data[0][0] = 13246;
        data[0][1] = "First product";
        data[0][2] = 1500;
        data[0][3] = 3;
        
        /*data[1][0] = 98752;
        data[1][1] = "Second product";
        data[1][2] = 50000;
        data[1][3] = 2;
        
        data[2][0] = 45679;
        data[2][1] = "Third product";
        data[2][2] = 16000;
        data[2][3] = 5;
        
        data[3][0] = 1329787;
        data[3][1] = "Fourth product";
        data[3][2] = 29990;
        data[3][3] = 4;*/

        

        return data;
    }

}