import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.HomePage;
import page.LoginPage;
import page.SendMessagePage;

@FixMethodOrder(MethodSorters.DEFAULT)
public class SendMessageTest extends AbstractTest{
    private final static String EMAIL = "koross@gmail.com";
    private final static String PASSWORD = "password";

    @BeforeClass
    public static void beforeAll() {
        attemptLogin(EMAIL, PASSWORD);
    }

    private static void attemptLogin(String email, String password) {
        driver.get("http://localhost:8080/login");
        new LoginPage(driver).attemptLogin(email, password);
    }

    // TODO: the user might not have joined any class or in any conversation
    private static void goToFirstClassConvo() {
        new WebDriverWait(driver, 3).until(driver -> driver.findElement(By.className("itemClass")));
        new HomePage(driver).goToFirstClassConvo();
        new WebDriverWait(driver, 3).until(driver -> driver.findElement(By.className("itemMessage")));
    }

    @Test
    public void pageLoadCorrectly() {
        new HomePage(driver);
    }

    @Test
    public void showMessage() throws Exception {
        goToFirstClassConvo();
    }

    @Test
    public void sendMessage() throws Exception {
        goToFirstClassConvo();
        SendMessagePage smPage = new SendMessagePage(driver);

        Random rand = new Random();
        int randomId = rand.nextInt(10000);
        String newMessage = "Test Message " + randomId;
        smPage.sendMessage(newMessage);

        Thread.sleep(1500);
        
        String lastMessage = smPage.getLastMessage();
        assertTrue(lastMessage.equals(newMessage));
    }
}
