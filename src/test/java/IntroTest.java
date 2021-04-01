import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class IntroTest {
    @Test
    public void firstTest() {
        // Telling the system where to find chromedriver on Mac.
        // System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        // Here's how to do it on Windows
        // System.setProperty("webdriver.chrome.driver",
        // "resources/windows/chromedriver.exe");

        // WebDriver driver = new ChromeDriver();
        // driver.get("https://www.saucedemo.com/");
        // driver.quit();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("div>h2")));
            System.out.println(firstResult.getAttribute("textContent"));

        } finally {
            driver.quit();
        }
    }

    @Test
    public void secondTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://selenium.dev");
            driver.findElement(By.cssSelector("a[href='/projects']")).click();
            driver.findElement(By.className("learn-more")).click();
        } finally {
            wait.until(presenceOfElementLocated(By.cssSelector("#cssSelector")));
            driver.quit();
        }
    }
}
