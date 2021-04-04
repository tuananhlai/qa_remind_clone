package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    protected WebDriver driver;

    private By emailBy = By.id("txtEmail");
    private By passwordBy = By.id("txtPassword");
    private By loginBtnBy = By.id("btnSignIn");
    private By formTitleBy = By.className("titleSignin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(formTitleBy));
    }

    public HomePage attemptLogin(String email, String password) {
        WebElement loginInput = driver.findElement(emailBy);
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(passwordBy);
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(loginBtnBy);
        loginBtn.click();

        return new HomePage(driver);
    }
}
