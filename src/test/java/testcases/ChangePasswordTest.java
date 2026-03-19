package testcases;

import Common.Constant.Constant;
import PageObjects.ChangePasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test (description =  "TC09 - User can change password successfully")
    public void TC09()
    {
        final String username = "ledieu021006@gmail.com";
        final String currentPassword = "1234@abc";
        final String newPassword = "1234@cba";

        // Step 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2: Login with valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(username, currentPassword);

        // Step 3: Click on "Change Password" tab
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

        // Step 4: Enter valid value into all fields
        changePasswordPage.changePassword(currentPassword, newPassword, newPassword);

        // Step 5: Verify message "Your password has been updated" appears
        String actualMsg = changePasswordPage.getSuccessMessage();
        String expectedMsg = "Your password has been updated";

        Assert.assertEquals(actualMsg, expectedMsg, "Password change message is incorrect!");        // Optional: Cập nhật password mới vào Constant để test khác dùng
    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
