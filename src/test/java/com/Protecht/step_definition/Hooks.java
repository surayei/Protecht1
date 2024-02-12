package com.Protecht.step_definition;

import com.Protecht.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    @Before("ui")
    public void setUp(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Driver.getDriver().manage().window().maximize();

    }

    @After("@ui")
    public void teardownMethod(Scenario scenario){
        if (scenario.isFailed()){
            byte[] screenshot =((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", scenario.getName());
        }

        Driver.closeDriver();
    }
}
