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

import page.LoginPage;

@OrderWith(Alphanumeric.class)
public class LoginTest {
    private static WebDriver driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    private final static String EMAIL = "koross@gmail.com";
    private final static String PASSWORD = "password";
    private final static String WRONG_EMAIL = "thisiswrong@fidjaovsdc.com";
    private final static String WRONG_PASSWORD = "thisiswrong";

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }

    @Before
    public void beforeEach() {
        driver.get("http://localhost:8080/login");
    }

    @After
    public void afterEach() {
        clearLocalStorage();
    }

    private void clearLocalStorage() {
        js.executeScript(String.format("window.localStorage.clear();"));
    }

    public static void attemptLogin(String email, String password) {
        driver.get("http://localhost:8080/login");
        WebElement loginInput = driver.findElement(By.id("txtEmail"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("btnSignIn"));
        loginBtn.click();
    }

    @Test
    public void loginWithCorrectInfo() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin(EMAIL, PASSWORD);
    }

    @Test
    public void loginWithIncorrectUsername() {
        LoginPage loginPage = new LoginPage(driver);
        assertThrows("User failed to login", Exception.class, () -> loginPage.attemptLogin(WRONG_EMAIL, PASSWORD));
    }

    @Test
    public void loginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        assertThrows("User failed to login", Exception.class, () -> loginPage.attemptLogin(EMAIL, WRONG_PASSWORD));
    }
}
