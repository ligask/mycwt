package com.mycwt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MycwtLoginPage {

    @FindBy(id = "forgot-password-button")
    private WebElement forgotPassowrdButton;

    @FindBy(id = "usernameField")
    private WebElement usernameField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    WebDriver driver;
    public MycwtLoginPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickForgotPassButton() {
        forgotPassowrdButton.click();
           }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void submit() {
        submitButton.click();
    }
}
