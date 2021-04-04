import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.DEFAULT)
public class SendMessageTest {
    private static WebDriver driver;
    private final static String EMAIL = "koross@gmail.com";
    private final static String PASSWORD = "password";

    @BeforeClass
    public static void beforeAll() {
        driver = new ChromeDriver();
        attemptLogin(EMAIL, PASSWORD);
    }

    @AfterClass
    public static void afterAll() {
        driver.quit();
    }

    private static void attemptLogin(String email, String password) {
        driver.get("http://localhost:8080/login");
        WebElement loginInput = driver.findElement(By.id("txtEmail"));
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("btnSignIn"));
        loginBtn.click();
    }

    // TODO: the user might not have joined any class or in any conversation
    private static void goToFirstClassConvo() {
        new WebDriverWait(driver, 3).until(driver -> driver.findElement(By.className("itemClass")));
        WebElement firstClassItem = driver.findElement(By.className("itemClass"));
        firstClassItem.click();
        WebElement messageTab = driver.findElement(By.className("tabMessage"));
        messageTab.click();
        WebElement firstConversation = driver.findElement(By.className("itemConversation"));
        firstConversation.click();
        new WebDriverWait(driver, 3).until(driver -> driver.findElement(By.className("itemMessage")));
    }

    @Test
    public void pageLoadCorrectly() {
        new WebDriverWait(driver, 3).until(driver -> driver.findElement(By.className("v-navigation-drawer__content")));
    }

    @Test
    public void showMessage() throws Exception {
        goToFirstClassConvo();
    }

    @Test
    public void sendMessage() throws Exception {
        goToFirstClassConvo();
        Random rand = new Random();
        int randomId = rand.nextInt(10000);
        String newMessage = "Test Message " + randomId;
        WebElement messageBoxInput = driver.findElement(By.id("message-text-box-input"));
        messageBoxInput.sendKeys(newMessage);
        WebElement sendBtn = driver.findElement(By.id("btnSend"));
        sendBtn.click();

        Thread.sleep(1500);

        WebElement lastMessage = driver.findElement(By.xpath("(//*[contains(@class, 'itemMessage')])[last()]"));
        assertTrue(lastMessage.findElement(By.tagName("p")).getText().equals(newMessage));
    }
}
