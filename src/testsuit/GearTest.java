package testsuit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart(){
        mouseHoverToElement(By.xpath("//span[normalize-space()='Gear']"));
        clickOnElement(By.xpath("//span[normalize-space()='Bags']"));

        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        String actMessage = getTextFromElement(By.xpath("//span[@class='base']"));
        verifyText("Invalid success message", "Overnight Duffle", actMessage);

        driver.findElement(By.id("qty")).clear();
        sendTextToElement(By.id("qty"),"3");
        clickOnElement(By.id("product-addtocart-button"));

        String actCartMsg = getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        verifyText("Invalid success message", "You added Overnight Duffle to your shopping cart.", actCartMsg);

        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        String actName = getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        verifyText("Invalid name", "Overnight Duffle",actName);

        Assert.assertEquals("3", driver.findElement(By.xpath("//input[@class='input-text qty']")).getAttribute("value"));

        String actPrc = getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"));
        verifyText("Invalid Price", "$135.00",actPrc);

        driver.findElement(By.xpath("//input[@class='input-text qty']")).clear();
        sendTextToElement(By.xpath("//input[@class='input-text qty']"), "5");
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));

        Assert.assertEquals("$225.00", getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']")));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
