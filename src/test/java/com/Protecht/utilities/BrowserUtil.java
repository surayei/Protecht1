package com.Protecht.utilities;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserUtil {


    public static void switchToNextTab(){

        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        String current = Driver.getDriver().getWindowHandle();
        for (String each : allWindowHandles) {
            if (!each.equalsIgnoreCase(current)){
                Driver.getDriver().switchTo().window(each);
                break;
            }

        }

    }
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static WebElement waitForClickablility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageToLoad(long time) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void  waitAndSwitchToFrame(WebElement frameElement){

        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(60));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }


}
