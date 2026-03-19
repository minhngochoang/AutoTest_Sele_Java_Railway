package testcases;

import Common.Constant.Constant;
import PageObjects.ForgotPasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordTest {

    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test (description = "TC12 - Errors display when password reset token is blank")
    public void TC12() {

        // Step 1. Navigate to QA Railway Login page
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 2. Click on "Forgot Password page" link
        ForgotPasswordPage forgotPage = loginPage.gotoForgotPasswordPage();

        // Step 3. Enter the email address of the created account in Pre-condition
        // Step 4. Click on "Send Instructions" button
        forgotPage.sendInstructions(Constant.USERNAME);

        // Verify
        Assert.assertTrue(false, "Error page displays after clicking 'Send Instructions'.");
    }

    @Test (description = "TC13 - Errors display if password and confirm password don't match")
    public void TC13() {

        // Step 1. Navigate to QA Railway Login pag
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 2. Click on "Forgot Password page" link
        ForgotPasswordPage forgotPage = loginPage.gotoForgotPasswordPage();

        // Step 3. Enter the email address of the created account in Pre-condition
        // Step 4. Click on "Send Instructions" button
        forgotPage.sendInstructions(Constant.USERNAME);

        // Vefify

        Assert.assertTrue(false, "Error page displays after clicking 'Send Instructions'.");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
