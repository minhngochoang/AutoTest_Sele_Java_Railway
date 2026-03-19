package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookTicketPage extends GeneralPage{

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By lblSuccessHeader = By.xpath("//div[@id='content']/h1");

    //Methods
    // TC14
    public void selectOption(String nameAttribute, String value) {
        String dynamicXpath = String.format("//select[@name='%s']", nameAttribute);
        WebElement element = Constant.WEBDRIVER.findElement(By.xpath(dynamicXpath));
        new Select(element).selectByVisibleText(value);
    }

    public void bookTicket(String date, String depart, String arrive, String seat, String amount) {
        selectOption("Date", date);
        selectOption("DepartStation", depart);
        selectOption("ArriveStation", arrive);
        selectOption("SeatType", seat);
        selectOption("TicketAmount", amount);

        WebElement btnBook = wait.until(ExpectedConditions.elementToBeClickable(btnBookTicket));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", btnBook);
    }

    public String getSuccessMessage() {
        return Constant.WEBDRIVER.findElement(lblSuccessHeader).getText();
    }

    // TC14
    public String getTicketDetails(String columnName) {

        String xpath = String.format(
                "//table//tr[last()]/td[count(//table//th[normalize-space(text())='%s']/preceding-sibling::th)+1]",
                columnName
        );

        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }

    // TC15
     public String getSelectedDropdownValue(String nameAttribute) {

        String dynamicXpath = String.format("//select[@name='%s']", nameAttribute);

        WebElement element = Constant.WEBDRIVER.findElement(By.xpath(dynamicXpath));
        Select select = new Select(element);

        return select.getFirstSelectedOption().getText();
    }

}



