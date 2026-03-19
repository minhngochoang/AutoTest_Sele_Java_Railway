package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TimetablePage extends GeneralPage {

    public BookTicketPage clickBookTicketForRoute(String departStation, String arriveStation) {

        String dynamicXpath = String.format(
                "//td[normalize-space(text())='%s']" +
                        "/following-sibling::td[normalize-space(text())='%s']" +
                        "/ancestor::tr[1]" +
                        "//a[text()='book ticket']",
                departStation,
                arriveStation
        );

        By bookTicketLink = By.xpath(dynamicXpath);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement bookTicketElement = wait.until(ExpectedConditions.elementToBeClickable(bookTicketLink));

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", bookTicketElement);

        return new BookTicketPage();
    }
}

