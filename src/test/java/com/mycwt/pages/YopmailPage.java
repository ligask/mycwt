package com.mycwt.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YopmailPage {

    @FindBy(id = "necesary")
    private WebElement acceptCookies;

    @FindBy(id = "login")
    private WebElement loginInput;

    @FindBy(id = "refreshbut")
    private WebElement goToInbox;

    @FindBy(id = "refresh")
    private WebElement refreshInbox;

    @FindBy(linkText = "RESET PASSWORD")
    private WebElement resetPassword;



    WebDriver driver;
    public YopmailPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setAcceptCookies() {
        acceptCookies.click();
    }

    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void checkInbox() {
        goToInbox.click();
    }

    public void refreshInbox() {
       refreshInbox.click();
    }

    public void resetPassword() {
        resetPassword.click();
    }
}
