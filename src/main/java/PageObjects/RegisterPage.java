package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends GeneralPage{

    private final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By txtPid = By.xpath("//input[@id='pid']");
    private final By btnRegister = By.xpath("//input[@type='submit' and @value='Register']");
    private final By lblSuccessMessage = By.xpath("//div[@id='content']/p"); //

    private final By lblErrorMessage = By.xpath("//div[@id='content']/p[@class='message error']");
    private final By lblPasswordError = By.xpath("//li[@class='password']//label[@class='validation-error']");
    private final By lblPidError = By.xpath("//li[@class='pid-number']//label[@class='validation-error']");

    // Elements
    public WebElement getTxtPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
    }

    public WebElement getTxtConfirmPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtConfirmPassword));
    }

    public WebElement getTxtPid() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtPid));
    }

    public WebElement getBtnRegister() {
        return wait.until(ExpectedConditions.elementToBeClickable(btnRegister));
    }

    // Methods
    public void register(String email, String password, String confirmPassword, String pid) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(email);
        getTxtPassword().sendKeys(password);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPid().sendKeys(pid);

        WebElement registerBtn = getBtnRegister();

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", registerBtn);
    }

    // TC07
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblSuccessMessage)).getText();
    }

    // TC08
    public String getRegisterErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessage)).getText();
    }

    // TC11
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblPasswordError)).getText();
    }
    public String getPidErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblPidError)).getText();
    }
}
