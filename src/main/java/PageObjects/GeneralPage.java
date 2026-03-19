package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GeneralPage {

    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[contains(@href, '/Account/Login')]");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeUser= By.xpath("//div[@class='account']");

    private final By lblPageHeader = By.xpath("//div[@id='content']/h1"); //Tiêu đề trang Login

    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[contains(@href, 'BookTicket')]");
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[contains(@href, '/ManageTicket')]");

    private final By tabChangePassword = By.xpath("//div[@id='menu']//a[contains(@href, '/ChangePassword')]");

    private final By tabRegister = By.xpath("//div[@id='menu']//a[contains(@href, '/Account/Register')]");

    private final By tabTimetable = By.xpath("//div[@id='menu']//a[contains(@href, 'TrainTimeListPage')]");

    // Elements
    protected WebElement getTabLogin(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tabLogin));
    }

    protected WebElement getTabLogout() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabLogout));
    }

    protected WebElement getLbWelcomeUser(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblWelcomeUser));
    }

    protected WebElement getTabBookTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabBookTicket));
    }

    protected WebElement getTabMyTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabMyTicket));
    }

    protected WebElement getTabChangePassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabChangePassword));
    }

    // TC10
    protected WebElement getTabRegister() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabRegister));
    }

    protected WebElement getTabTimetable() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabTimetable));
    }

    // Methods
    public String getWelcomeUser(){
        return this.getLbWelcomeUser().getText();
    }

    public LoginPage gotoLoginPage(){
        getTabLogin().click();
        return new LoginPage();
    }

    // TC04
    public LoginPage gotoBookTicketPage_ExpectLoginPage() {
        getTabBookTicket().click();
        return new LoginPage();
    }
    public String getPageHeaderText() {
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lblPageHeader));
        return headerElement.getText();
    }

    //TC06
    private boolean isTabDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMyTicketTabDisplayed() {
        return isTabDisplayed(tabMyTicket);
    }

    public boolean isChangePasswordTabDisplayed() {
        return isTabDisplayed(tabChangePassword);
    }

    public boolean isLogoutTabDisplayed() {
        return isTabDisplayed(tabLogout);
    }

    public MyTicketPage gotoMyTicketPage() {
        getTabMyTicket().click();
        return new MyTicketPage();
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        getTabChangePassword().click();
        return new ChangePasswordPage();
    }

    //TC10
    public RegisterPage gotoRegisterPage() {
        getTabRegister().click();
        return new RegisterPage();
    }

    // TC14
    public BookTicketPage gotoBookTicketPage() {
        getTabBookTicket().click();
        return new BookTicketPage();
    }

    // TC15
    public TimetablePage gotoTimetablePage() {
        getTabTimetable().click();
        return new TimetablePage();

    }

}
