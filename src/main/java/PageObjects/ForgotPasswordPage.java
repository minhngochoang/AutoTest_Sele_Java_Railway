package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends GeneralPage{

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    // Methods
    public void sendInstructions(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(email);

        wait.until(ExpectedConditions.elementToBeClickable(btnSendInstructions)).click();
    }
}
