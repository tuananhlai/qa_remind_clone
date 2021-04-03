import static org.junit.Assert.assertThrows;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    private static WebDriver driver = new ChromeDriver();

    public static void attemptLogin(String email, String password) {
        driver.get("http://localhost:8080");
        WebElement loginInput = driver.findElement(By.id("txtEmail"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("btnSignIn"));
        loginBtn.click();
    }

    @Test
    public void loginWithCorrectInfo() {
        try {
            attemptLogin("koross@gmail.com", "password");

            new WebDriverWait(driver, 3)
                    .until(driver -> driver.findElement(By.className("v-navigation-drawer__content")));
        } finally {
            new WebDriverWait(driver, 3);
        }
    }

    @Test
    public void loginWithIncorrectUsername() {
        try {
            attemptLogin("korossfasfas@gmafasfafil.com", "password");
            assertThrows("User failed to login", Exception.class, () -> new WebDriverWait(driver, 3)
                    .until(driver -> driver.findElement(By.className("v-navigation-drawer__content"))));
        } finally {

        }
    }

    @Test
    public void loginWithIncorrectPassword() {
        try {
            attemptLogin("koross@gmail.com", "passwords");
            assertThrows("User failed to login", Exception.class, () -> new WebDriverWait(driver, 3)
                    .until(driver -> driver.findElement(By.className("v-navigation-drawer__content"))));
        } finally {

        }
    }

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }
}
