package com.Protecht.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


import java.time.Duration;

public class Driver {

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browserType = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.getProperty("browser");

            switch (browserType) {
                case "chrome":
                    ChromeOptions options=new ChromeOptions();
                    options.addArguments("--disable-javascript");
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":

                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "headless-chrome":

                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("--headless=new");
                    driverPool.set(new ChromeDriver(option));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;


            }
        }

        return driverPool.get();

    }

    public static void closeDriver() {
        if (driverPool.get() != null) {

            driverPool.get().quit();

            driverPool.remove();
        }
    }
}
