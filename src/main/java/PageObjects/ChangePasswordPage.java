package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePasswordPage extends GeneralPage {

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private final By lblPageHeader = By.xpath("//div[@id='content']/h1"); //Tieu de trang Change Password

    private static final By txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
    private static final By txtNewPassword = By.xpath("//input[@id='newPassword']");
    private static final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private static final By btnChangePassword = By.xpath("//input[@type='submit' and @value='Change Password']");
    private static final By lblSuccessMsg = By.xpath("//div[@id='content']//p[@class='message success']");

    // Elements
    private WebElement getTxtCurrentPassword() {
        // Đảm bảo ô hiện tại hiển thị
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtCurrentPassword));
    }

    private WebElement getTxtNewPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtNewPassword));
    }

    private WebElement getTxtConfirmPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtConfirmPassword));
    }

    private WebElement getBtnChangePassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(btnChangePassword));
    }
    public boolean isPageTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(lblPageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // TC09
    public void changePassword(String currentPwd, String newPwd, String confirmNewPwd) {

        getTxtCurrentPassword().sendKeys(currentPwd);
        getTxtNewPassword().sendKeys(newPwd);
        getTxtConfirmPassword().sendKeys(confirmNewPwd);

        WebElement changePasswordBtn = getBtnChangePassword();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", changePasswordBtn);
    }
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblSuccessMsg)).getText();
    }
}
