import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@OrderWith(Alphanumeric.class)
public class LoginTest {
    private static WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }

    @Before
    public void beforeEach() {
        driver.manage().deleteAllCookies();
    }

    @After
    public void afterEach() {
        clearLocalStorage();
    }

    private void clearLocalStorage() {
        js.executeScript(String.format("window.localStorage.clear();"));
    }

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
}
