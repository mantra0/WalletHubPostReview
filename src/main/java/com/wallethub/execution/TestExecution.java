package com.wallethub.execution;
import com.wallethub.resources.Utilty;
import com.wallethub.resources.WebdriverWrapper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestExecution extends WebdriverWrapper {



    @BeforeTest
    void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\wallethub\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://wallethub.com/join/light");
    }

    @Test
    void signUp(){
        Utilty.signUp("username", "password");
    }

    @Test(dependsOnMethods = "signUp")
    void checkReview(){
        Utilty.submitReview();
        Utilty.checkReview();
    }
}
