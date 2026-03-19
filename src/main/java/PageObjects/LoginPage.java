package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends GeneralPage {

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private final By txtUsername = By.xpath("//input[@id = 'username']");
    private final By txtPassword = By.xpath("//input[@id = 'password']");
    private final By btnLogin = By.xpath("//input[contains(@value, 'Login')]");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    private final By lnkForgotPassword = By.xpath("//a[contains(@href, 'ForgotPassword')]");

    // Element
    public WebElement getTxtUsername() {
        return wait.until(ExpectedConditions.elementToBeClickable(txtUsername));
    }
    public WebElement getTxtPassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(txtPassword));
    }
    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLogin);
    }
    public WebElement getLbLoginErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblLoginErrorMsg));
    }

    //Methods
    public String getLoginErrorMsg(){
        return this.getLbLoginErrorMsg().getText();
    }

    //TC05
    public void attemptLogin(String username, String password) {
        getTxtUsername().clear();
        getTxtUsername().sendKeys(username);
        getTxtPassword().clear();
        getTxtPassword().sendKeys(password);

        WebElement loginButton = getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblLoginErrorMsg));
    }
    public String getLoginErrorText() {
        try {
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
            WebElement lblError = wait.until(ExpectedConditions.visibilityOfElementLocated(lblLoginErrorMsg));
            return lblError.getText().trim();
        } catch (Exception e) {
            return "Khong tim thay thong bao loi.";
        }
    }

    public HomePage login(String username, String password) {
        getTxtUsername().clear();
        getTxtUsername().sendKeys(username);

        getTxtPassword().clear();
        getTxtPassword().sendKeys(password);

        WebElement loginButton = getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        return new HomePage();
    }

    // TC 12
    public ForgotPasswordPage gotoForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(lnkForgotPassword)).click();
        return new ForgotPasswordPage();
    }
}
