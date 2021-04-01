import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    private static WebDriver driver = new ChromeDriver();

    @Test
    public void loginWithCorrectInfo() {
        try {
            driver.get("http://localhost:8080");
            WebElement loginInput = driver.findElement(By.id("input-17"));
            loginInput.sendKeys("koross@gmail.com");
            WebElement passwordInput = driver.findElement(By.id("input-20"));
            passwordInput.sendKeys("password");
            WebElement loginBtn = driver.findElement(By.cssSelector("form > button"));
            loginBtn.click();

            new WebDriverWait(driver, 3)
                    .until(driver -> driver.findElement(By.className("v-navigation-drawer__content")));
        } finally {

        }
    }

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }
}
