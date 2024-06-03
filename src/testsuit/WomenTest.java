package testsuit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() {
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        mouseHoverToElement(By.id("ui-id-9"));
        clickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));

        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li/div/div/strong/a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productList) {
            productNames.add(element.getText().toUpperCase());
        }

        Collections.sort(productNames);
        selectByValueFromDropDown(By.id("sorter"), "name");      //select sort by product name
        List<WebElement> productList2 = driver.findElements(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li/div/div/strong/a"));
        List<String> sortedList = new ArrayList<>();
        for (WebElement element : productList2) {
            sortedList.add(element.getText().toUpperCase());
        }
        Assert.assertEquals(productNames, sortedList);

    }

    @Test
    public void verifyTheSortByPriceFilter() {
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        mouseHoverToElement(By.id("ui-id-9"));
        clickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));

        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li/div/div/div/span/span/span[2]"));
        List<String> prices = new ArrayList<>();
        for (WebElement element : priceList) {
            prices.add(element.getText().replaceAll("$",""));
        }

        Collections.sort(prices);
        selectByValueFromDropDown(By.id("sorter"), "price");      //select sort by price
        List<WebElement> priceList2 = driver.findElements(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li/div/div/div/span/span/span[2]"));
        List<String> sortedList = new ArrayList<>();
        for (WebElement element : priceList2) {
            sortedList.add(element.getText());
        }
        Assert.assertEquals(prices, sortedList);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart(){
        mouseHoverToElement(By.xpath("//span[normalize-space()='Men']"));
        mouseHoverToElement(By.id("ui-id-18"));
        clickOnElement(By.xpath("//a[@id='ui-id-23']//span[contains(text(),'Pants')]"));

        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        clickOnElement(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-size-143-item-175']"));

        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        clickOnElement(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-color-93-item-49']"));

        mouseHoverToElement(By.xpath("//a[normalize-space()='Cronus Yoga Pant']"));
        clickOnElement(By.xpath("//body[1]/div[2]/main[1]/div[3]/div[1]/div[3]/ol[1]/li[1]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/button[1]/span[1]"));

        String expMessage = "You added Cronus Yoga Pant to your shopping cart.";
        String actMessage = getTextFromElement(By.xpath("//div[@class='message-success success message']"));
        verifyText("Invalid success message", expMessage, actMessage);

        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));     //click on shopping cart from message

        String expCartHeading = "Shopping Cart";
        String actCartHeading = getTextFromElement(By.xpath("//span[@class='base']"));
        verifyText("Invalid heading", expCartHeading,actCartHeading);

        String actName = getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']"));
        verifyText("Invalid heading", "Cronus Yoga Pant",actName);

        String actSizeHeading = getTextFromElement(By.xpath("//dd[contains(text(),'32')]"));
        verifyText("Invalid heading", "32",actSizeHeading);

        String actColourHeading = getTextFromElement(By.xpath("//dd[contains(text(),'Black')]"));
        verifyText("Invalid heading", "Black",actColourHeading);

    }




    @After
    public void tearDown() {
//        closeBrowser();
    }
}
