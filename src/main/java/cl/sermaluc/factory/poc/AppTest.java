package cl.sermaluc.factory.poc;

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

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[1][4];

        data[0][0] = 13246;
        data[0][1] = "First product";
        data[0][2] = 1500;
        data[0][3] = 3;

        

        return data;
    }

}