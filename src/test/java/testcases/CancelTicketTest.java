package testcases;

import Common.Common.DateUtils;
import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObjects.BookTicketPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyTicketPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelTicketTest {

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test (description = "TC16 - User can cancel a ticket")
    public void TC16() {

        // Step1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Login with a valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 3. Book a ticket
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        String departDate = DateUtils.getFutureDateForRailway(5);
        bookTicketPage.bookTicket(departDate, "Sài Gòn", "Huế", "Soft seat", "1");

        // Lấy ID vé từ URL
        String ticketID = Utilities.getTicketIDFromURL(Constant.WEBDRIVER);

        // HUỶ VÉ
        // Step 4. Click on "My ticket" tab
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();

        // Step 5. Click on "Cancel" button of ticket which user want to cancel.
        // Step 6. Click on "OK" button on Confirmation message "Are you sure?"
        myTicketPage.cancelTicketByID(ticketID);

        // Verify
        boolean isCancelled = !myTicketPage.isTicketDisplayed(ticketID);

        Assert.assertTrue(isCancelled, "Lỗi: Vé vẫn còn tồn tại trong bảng sau khi hủy.");
        System.out.println("TC16 Passed: Vé có ID " + ticketID + " đã được hủy thành công!");
    }


    @AfterMethod
    public void afterMethod() {
        Constant.WEBDRIVER.quit();
    }
}
