package testcases;

import Common.Constant.Constant;
import PageObjects.ChangePasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyTicketPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test (description = "TC01 - User can log into Railway with valid username and password")
    public void TC01() {

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Enter valid Email and Password
        HomePage actualHomePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Verify
        String actualMsg = actualHomePage.getWelcomeUser().trim();  // Không hiện thông báo chào mừng
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed correctly!");
    }

    @Test (description = "TC02 - User can't login with blank Username textbox")
    public void TC02() {
        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox
        loginPage.getTxtUsername().sendKeys("");
        loginPage.getTxtPassword().sendKeys(Constant.PASSWORD);

        // Step 4. Click on "Login" button
        WebElement loginButton = loginPage.getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        // Verify
        String actualMessage =  loginPage.getLoginErrorMsg();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should be displayed");
    }

    @Test (description = "TC03 - User cannot log into Railway with invalid password")
    public void TC03() {

        //Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Enter valid Email and invalid Password
        loginPage.getTxtUsername().sendKeys(Constant.USERNAME);
        loginPage.getTxtPassword().sendKeys("invalid_password");

        // Step 4. Click on "Login" button
        WebElement loginButton = loginPage.getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        // Verify
        String actualMessage =  loginPage.getLoginErrorMsg();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMessage, expectedMessage, "Error message should be displayed");
    }

    @Test (description = "TC04 - Login page displays when un-logged User clicks on Book ticket tab")
    public void TC04() {

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Book ticket" tab
        LoginPage loginPage = homePage.gotoBookTicketPage_ExpectLoginPage();

        // Verify
        String actualTitle = loginPage.getPageHeaderText();
        String expectedTitle = "Login Page";

        Assert.assertEquals(actualTitle, expectedTitle, "Login page was not displayed correctly");
    }

    @Test (description = "TC05 - System shows message when user enters wrong password several times")
    public void TC05() {

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3, 4, 5: Lặp lại chính xác 4 LẦN
        for (int i = 1; i <= 4; i++) {
            System.out.println("Dang nhap sai lan thu: " + i);

            loginPage.attemptLogin(Constant.USERNAME, "wrongPassword");
        }

        // Vefiry
        String actualMessage = loginPage.getLoginErrorText();
        String expectedMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMessage, expectedMessage, "Error message content is not correct after 4 attempts!");
    }

    @Test (description = "TC06 - Additional pages display once user logged in")
    public void TC06() {

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Login with valid account
        HomePage actualHomePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Verify 1: "My ticket", "Change password" and "Logout" tabs are displayed
        Assert.assertTrue(actualHomePage.isMyTicketTabDisplayed(), "My Ticket tab is not displayed.");
        Assert.assertTrue(actualHomePage.isChangePasswordTabDisplayed(), "Change Password tab is not displayed.");
        Assert.assertTrue(actualHomePage.isLogoutTabDisplayed(), "Logout tab is n displayed.");

        // Verify 2: Click "My ticket" tab, user will be directed to My Ticket page
        MyTicketPage myTicketPage = actualHomePage.gotoMyTicketPage();

        Assert.assertTrue(myTicketPage.isPageTitleDisplayed(), "My Ticket page did not load.");

        // Verify 3: Click "Change password" tab, user will be directed to Change password page
        ChangePasswordPage changePasswordPage = myTicketPage.gotoChangePasswordPage();

        Assert.assertTrue(changePasswordPage.isPageTitleDisplayed(), "Change Password page did not load.");
    }

    @Test(description = "TC08 - User can't login with an account hasn't been activated")
    public void TC08() {

        String unactivated_user = "wpotmaster@gmail.com"; // Gmail chưa activated
        String password = "1234@cba";

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Enter username and password of account hasn't been activated.
        // Step 4. Click on "Login" button
        loginPage.login(unactivated_user, password);

        // Verify
        String actualError = loginPage.getLoginErrorMsg(); // Tài khoản vẫn đăng nhập đượ
        String expectedError = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualError, expectedError, "Lỗi: Thông báo hiển thị không khớp");

    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
