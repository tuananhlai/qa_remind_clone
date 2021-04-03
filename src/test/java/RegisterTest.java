import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTest {
    private static WebDriver driver;

    private final static String EXISTING_EMAIL = "koross@gmail.com";

    @BeforeClass
    public static void beforeAll() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }

    @Before
    public void beforeEach() {
        driver.get("http://localhost:8080/register");
        driver.manage().deleteAllCookies();
    }

    private static void attemptLogin(String email, String password) {
        WebElement loginInput = driver.findElement(By.id("txtEmail"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.cssSelector("form > button"));
        loginBtn.click();
    }

    private static void attemptRegister(String username, String email, String password, String confirmPassword) {

    }

    @Test
    public void pageLoadCorrectly() {
        WebElement registerTitle = driver.findElement(By.className("titleSignin"));
        assertTrue("Correct Title", registerTitle.getText().equals("Sign Up"));
    }

    @Test
    public void registerCorrectly() {
        try {
            ArrayList<WebElement> inputs = (ArrayList<WebElement>) driver
                    .findElements(By.cssSelector("form.v-form input"));
            WebElement submitBtn = driver.findElement(By.cssSelector("form.v-form button.v-btn"));
            for (int i = 0; i < inputs.size(); i++) {
                System.out.println(inputs.get(i));
            }
            Random rand = new Random();

            int randomId = rand.nextInt(1000);

            String newUser = "testuser" + randomId;
            String newEmail = "testuser" + randomId + "@email.com";
            String newPassword = "password";

            inputs.get(0).sendKeys(newUser);
            inputs.get(1).sendKeys(newEmail);
            inputs.get(2).sendKeys(newPassword);
            inputs.get(3).sendKeys(newPassword);
            inputs.get(4).sendKeys(Keys.DOWN);
            inputs.get(4).sendKeys(Keys.DOWN);
            inputs.get(4).sendKeys(Keys.DOWN);
            inputs.get(4).sendKeys(Keys.RETURN);

            submitBtn.click();

            WebElement title = driver.findElement(By.className("titleSignin"));

            Thread.sleep(3000);

            assertTrue("transition to login page", title.getText().equals("Sign In"));

            attemptLogin(newEmail, newPassword);

            new WebDriverWait(driver, 3)
                    .until(driver -> driver.findElement(By.className("v-navigation-drawer__content")));

        } catch (InterruptedException e) {

        } finally {

        }
    }
}
