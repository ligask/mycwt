package com.mycwt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MycwtSetNewPassPage {

    @FindBy(id = "newPassword")
    private WebElement newPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(xpath = "//p[contains(.,'Your password does not meet the password policy')]")
    private WebElement passwordPolicyMessage;

    @FindBy(id = "newPass-help-block")
    private WebElement helpBlock;

    @FindBy(id = "login-url-continue")
    private WebElement doneButton;

    @FindBy(id = "username-input")
    private WebElement usernameInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(id = "serverError")
    private WebElement serverError;

    public WebDriver driver;

    public MycwtSetNewPassPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setNewPassword(String password) {
        newPassword.clear();
        newPassword.sendKeys(password);
        submitButton.click();
    }

    public void assertMessageColor(String color) {
        WebElement text = helpBlock;
        String rgba = text.getCssValue("color");
        String hex = Color.fromString(rgba).asHex();
        Assert.assertTrue(hex.equals(color));
    }

    public void assertServerMessageColor(String color) {
        WebElement text = serverError;
        String rgba = text.getCssValue("color");
        String hex = Color.fromString(rgba).asHex();
        Assert.assertTrue(hex.equals(color));
    }

    public void clickDone() {
        doneButton.click();
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
        System.out.println(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        System.out.println(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
