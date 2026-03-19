package testcases;

import Common.Common.TestHelper;
import Common.Constant.Constant;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test (description = "TC07 - User can create new account")
    public void TC07() {

        String EMAIL = TestHelper.generateUniqueEmail();
        String PASSWORD = "password123";
        String CONFIRM_PASSWORD = "password123";
        String PID = "123456789";

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Register" tab
        RegisterPage registerPage = homePage.gotoRegisterPage();

        // Tep 3. Enter valid information into all fields
        registerPage.register(EMAIL, PASSWORD, CONFIRM_PASSWORD, PID);

        // Verify
        String actualMsg = registerPage.getSuccessMessage();
        String expectedMsg = "Thank you for registering your account";

        // Xác minh thông báo thành công
        Assert.assertEquals(actualMsg, expectedMsg, "Thông báo đăng ký thành công không khớp.");

    }

    @Test (description = "TC10 - User can't create account with Confirm password is not the same with Password")
    public void TC10() {

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Register" tab
        RegisterPage registerPage = homePage.gotoRegisterPage();

        // Step 3. Enter valid information into all fields except "Confirm password" is not the same with "Password"
        String email = "rappergb@gmail.com";
        String password = "1234@abcd";
        String confirmPassword = "1234@abc"; // Khong khop
        String pid = "123456789";

        // Step 4. Click on "Register" button
        registerPage.register(email, password, confirmPassword, pid);

        // Verify
        String actualErrorMsg = registerPage.getRegisterErrorMessage();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed correctly!");
    }

    @Test (description = "TC11 - User can't create account while password and PID fields are empty")
    public void TC11() {

        String email = TestHelper.generateUniqueEmail();
        String emptyPassword = "";
        String emptyPid = "";

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Register" tab
        RegisterPage registerPage = homePage.gotoRegisterPage();

        // Step 3. Enter valid email address and leave other fields empty
        // Step 4. Click on "Register" button
        registerPage.register(email, emptyPassword, emptyPassword, emptyPid);

        // Verify
        String expectedGeneralMsg = "There're errors in the form. Please correct the errors and try again.";
        String expectedPassMsg = "Invalid password length";
        String expectedPidMsg = "Invalid ID length";

        String actualGeneralMsg = registerPage.getRegisterErrorMessage();
        Assert.assertEquals(actualGeneralMsg, expectedGeneralMsg, "General error message is incorrect.");

        String actualPassMsg = registerPage.getPasswordErrorText();
        Assert.assertEquals(actualPassMsg, expectedPassMsg, "Password length error is incorrect.");

        String actualPidMsg = registerPage.getPidErrorText();
        Assert.assertEquals(actualPidMsg, expectedPidMsg, "PID length error is incorrect.");
    }
    
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
