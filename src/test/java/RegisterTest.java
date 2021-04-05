import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import page.LoginPage;
import page.RegisterPage;

public class RegisterTest extends AbstractTest {
    private final static String EXISTING_EMAIL = "koross@gmail.com";

    @Before
    public void beforeEach() {
        driver.get("http://localhost:8080/register");
    }

    @Test
    public void pageLoadCorrectly() {
        String registerTitle = new RegisterPage(driver).getFormTitle();
        assertTrue("Correct Title", registerTitle.equals("Sign Up"));
    }

    @Test
    public void registerCorrectly() {
        RegisterPage registerPage = new RegisterPage(driver);
        Random rand = new Random();

        int randomId = rand.nextInt(1000);

        String newUser = "testuser" + randomId;
        String newEmail = "testuser" + randomId + "@email.com";
        String newPassword = "password";

        LoginPage loginPage = registerPage.attemptRegister(newUser, newEmail, newPassword, newPassword);
        loginPage.attemptLogin(newEmail, newPassword);
    }

    @Test
    public void registerWithExistingEmail() {
        RegisterPage registerPage = new RegisterPage(driver);
        assertThrows(Exception.class,
                () -> registerPage.attemptRegister("newUser", EXISTING_EMAIL, "password", "password"));
    }
}
