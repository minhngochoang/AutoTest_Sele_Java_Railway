package Common.Common;
import org.openqa.selenium.WebDriver;
public class Utilities
{
    // Mở URL và phóng to trình duyệt
    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

      // TC16
    public static String getTicketIDFromURL(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("id=")) {
            // Cắt chuỗi và lấy phần sau "id="
            return currentUrl.split("id=")[1];
        }
        return null;
    }

}

