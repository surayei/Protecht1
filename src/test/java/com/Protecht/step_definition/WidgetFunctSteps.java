package com.Protecht.step_definition;

import com.Protecht.pages.UITestPage;
import com.Protecht.utilities.BrowserUtil;
import com.Protecht.utilities.ConfigurationReader;
import com.Protecht.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class WidgetFunctSteps {

    UITestPage uiTestPage = new UITestPage();

    @Given("user is on the widget page")
    public void userIsOnTheWidgetPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("uiUrl"));
        BrowserUtil.waitFor(10);
    }

    @When("user clicks on {string} link")
    public void userClicksOnLink(String footer) {
        BrowserUtil.waitForPageToLoad(60);
        BrowserUtil.waitFor(5);
        BrowserUtil.waitAndSwitchToFrame(uiTestPage.iframe);
        uiTestPage.clickFooterLinkByText(footer);
        Driver.getDriver().switchTo().defaultContent();

    }

    @When("url should be {string}")
    public void titleShouldBeAndUrlShouldBe(String expectedUrl) {


        BrowserUtil.switchToNextTab();


        BrowserUtil.waitFor(6);

        Assert.assertEquals(expectedUrl, Driver.getDriver().getCurrentUrl());

    }

    @When("user changes the currency to GBP")
    public void user_changes_the_currency_to_gbp() {
        BrowserUtil.waitFor(1);
        uiTestPage.defaultCurrency.click();
        uiTestPage.gbpCurrency.click();


    }

    @When("clicks the configure button")
    public void clicks_the_configure_button() {
        BrowserUtil.waitFor(1);
        uiTestPage.configureButton.click();

    }

    @When("user should verify that the currency formats in the widget are updated to match the GBP {string} format")
    public void user_should_verify_that_the_currency_formats_in_the_widget_are_updated_to_match_the_gbp_format(String currencyFormat) {

        BrowserUtil.waitForPageToLoad(60);
        Driver.getDriver().switchTo().frame("tg-widget");
        String actualCurrency = uiTestPage.gbpFormat.getText();
        Assert.assertEquals(currencyFormat, actualCurrency);
        Driver.getDriver().switchTo().defaultContent();


    }

    @When("user adds additional items with costs {string} to the widget")
    public void user_adds_additional_items_with_costs_to_the_widget(String cost) {

        uiTestPage.productButton.click();
        uiTestPage.fanshield.click();
        BrowserUtil.waitFor(1);
        uiTestPage.closeIcon.click();
        uiTestPage.costOfItem.sendKeys(cost);
        uiTestPage.plusIcon.click();

    }

    @When("user clicks the configure button")
    public void user_clicks_the_configure_button() {
        BrowserUtil.waitFor(1);
        uiTestPage.configureButton.click();

    }

    @Then("user should validate that the price {string} and cost {string} of coverage in the widget have been updated accordingly")
    public void user_should_validate_that_the_price_and_cost_of_coverage_in_the_widget_have_been_updated_accordingly(String expectedPrice, String expectedCost) {

        Driver.getDriver().switchTo().frame("tg-widget");

        String actualPrice = uiTestPage.productPrice.getText();
        Assert.assertEquals(expectedPrice, actualPrice);

        String actualCost = uiTestPage.productCost.getText();
        Assert.assertEquals(expectedCost, actualCost);

        Driver.getDriver().switchTo().defaultContent();

    }

}
