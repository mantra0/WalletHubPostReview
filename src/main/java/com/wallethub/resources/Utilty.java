package com.wallethub.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilty extends WebdriverWrapper {


    static Actions action = new Actions(driver);
    static WebDriverWait wait = new WebDriverWait(driver, 20);

    private static String review = "random text (minimum of 200 characters) random text (minimum of 200 characters) random text " +
            "(minimum of 200 characters) random text (minimum of 200 characters) random text (minimum of 200 characters)";

    public static void signUp(String USERNAME, String PASSWORD){
        try{
            driver.findElement(By.cssSelector("[name='em']")).sendKeys(USERNAME);
            driver.findElement(By.cssSelector("[name='pw1']")).sendKeys(PASSWORD);
            driver.findElement(By.cssSelector("[name='pw2']")).sendKeys(PASSWORD);
            driver.findElement(By.cssSelector(".check")).click();
            driver.findElement(By.xpath("//*[text()='Join']")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'change your')]")));
            driver.findElement(By.cssSelector(".txt")).click();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void submitReview(){
        try{
            driver.get("https://wallethub.com/profile/test-insurance-company-13732055i");
            scrollDown();
            action.moveToElement(driver.findElement(By.cssSelector("[class*='review-action'] .rvs-star-svg:nth-child(5)"))).build().perform();
            Thread.sleep(1500);
            driver.findElement(By.cssSelector("[class*='review-action'] .rvs-star-svg:nth-child(5)")).click();
            driver.findElement(By.cssSelector(".wrev-drp .dropdown-placeholder")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[text()='Health Insurance']")).click();
            driver.findElement(By.cssSelector(".android [class*='textarea']")).sendKeys(review);
            driver.findElement(By.xpath("//*[text()='Submit']")).click();
            Thread.sleep(30000); //Wait provided to verify the email
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void scrollDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(2000);
    }


    public static void checkReview(){
        try{
            Thread.sleep(1000);
            driver.get("https://wallethub.com/profile/whatbe/reviews/");
            driver.findElement(By.xpath("//a[contains(@href,'whatbe/reviews')]")).click();
            String textOnPage = driver.findElement(By.cssSelector(".reviews p")).getText();
            Assert.assertEquals(review, textOnPage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
