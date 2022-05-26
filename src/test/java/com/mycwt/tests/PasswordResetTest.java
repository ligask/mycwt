package com.mycwt.tests;

import com.mycwt.pages.MycwtLoginPage;
import com.mycwt.pages.MycwtSetNewPassPage;
import com.mycwt.pages.YopmailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class PasswordResetTest extends BaseTest {


    @Test
    public void passwordResetTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        MycwtLoginPage mycwtLoginPage = new MycwtLoginPage(driver);
        YopmailPage yopmailPage = new YopmailPage(driver);
        MycwtSetNewPassPage mycwtSetNewPassPage = new MycwtSetNewPassPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forgot-password-button")));
        mycwtLoginPage.clickForgotPassButton();
        mycwtLoginPage.enterUsername("plata8@yopmail.com");
        mycwtLoginPage.submit();

        // opening Yopmail
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.yopmail.com");

        yopmailPage.setAcceptCookies();
        yopmailPage.enterLogin("plata8");
        yopmailPage.checkInbox();

        // waiting for new email
        Thread.sleep(13000);
        yopmailPage.refreshInbox();
        driver.switchTo().frame("ifmail");
        yopmailPage.resetPassword();

        // switching to new tab
        String oldTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals((oldTab))) {
                driver.switchTo().window(tab);
            }
        }

        // assert incorrect passwords and validate messages display in right color
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPassword")));
        mycwtSetNewPassPage.setNewPassword("test");          // too short
        mycwtSetNewPassPage.assertMessageColor("#ff0c3e");
        mycwtSetNewPassPage.setNewPassword("Qwerty23!!Qwerty23!!Qwerty23!!Qwerty23!!"); // too long
        mycwtSetNewPassPage.assertMessageColor("#ff0c3e");
        mycwtSetNewPassPage.setNewPassword("testtesttest");  // doesn't contain combination of letters, numbers and symbols
        mycwtSetNewPassPage.assertMessageColor("#ff0c3e");
        mycwtSetNewPassPage.setNewPassword("Qwerty32!");  // not different from 5 previous passwords
        mycwtSetNewPassPage.assertServerMessageColor("#ff0c3e");
        mycwtSetNewPassPage.setNewPassword("plata8@yopmail.com");  // same as username
        mycwtSetNewPassPage.assertServerMessageColor("#ff0c3e");

        // setting correct password
        mycwtSetNewPassPage.setNewPassword("Qwerty33!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-url-continue")));
        mycwtSetNewPassPage.clickDone();

        // login with correct credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-input")));
        mycwtSetNewPassPage.enterUsername("plata8@yopmail.com");
        mycwtSetNewPassPage.enterPassword("Qwerty33!");
        mycwtSetNewPassPage.clickSubmit();
    }
}
