package com.Protecht.pages;

import com.Protecht.utilities.BrowserUtil;
import com.Protecht.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UITestPage {

    public UITestPage () {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//div[.='USD']")
    public WebElement defaultCurrency;

    @FindBy(xpath = "//li[@data-value='GBP']")
    public WebElement gbpCurrency;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement configureButton;


    @FindBy(xpath = "//span[.='£100.00']")
    public WebElement gbpFormat;

    @FindBy(xpath = "//iframe")
    public WebElement iframe;

    @FindBy(xpath = "//div[@aria-labelledby='select-product']")
    public WebElement productButton;

    @FindBy(xpath = "//li[.='Fanshield']")
    public WebElement fanshield;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorPrimary MuiIconButton-edgeStart MuiIconButton-sizeMedium css-1ar9iec']")
    public WebElement closeIcon;

    @FindBy(xpath = "//input[@id='mui-2']")
    public WebElement costOfItem;

    @FindBy(xpath = "//button[@aria-label='add Cost of item']//span[@class='MuiButton-endIcon MuiButton-iconSizeSmall css-kcxyz4']//*[name()='svg']")
    public WebElement plusIcon;

    @FindBy(xpath = "//span[.='£45.00']")
    public WebElement productPrice;

    @FindBy(xpath = "//span[.='£3.16']")
    public WebElement productCost;


public void clickFooterLinkByText(String text){
    String locator="//a[contains(.,'"+text+"')]";
  WebElement webElement= Driver.getDriver().findElement(By.xpath(locator));
    BrowserUtil.waitForClickablility(webElement,30);

    webElement.click();
}







}
