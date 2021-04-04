package page;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    protected WebDriver driver;
    private By formTitleBy = By.className("titleSignUp");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(formTitleBy));
    }

    public LoginPage attemptRegister(String newUser, String newEmail, String newPassword, String confirmPassword) {
        ArrayList<WebElement> inputs = (ArrayList<WebElement>) driver.findElements(By.cssSelector("form.v-form input"));
        WebElement submitBtn = driver.findElement(By.id("btnRegister"));

        inputs.get(0).sendKeys(newUser);
        inputs.get(1).sendKeys(newEmail);
        inputs.get(2).sendKeys(newPassword);
        inputs.get(3).sendKeys(confirmPassword);
        // select role as 'Student'
        inputs.get(4).sendKeys(Keys.DOWN);
        inputs.get(4).sendKeys(Keys.DOWN);
        inputs.get(4).sendKeys(Keys.DOWN);
        inputs.get(4).sendKeys(Keys.RETURN);

        submitBtn.click();

        return new LoginPage(driver);
    }

    public String getFormTitle() {
        return driver.findElement(formTitleBy).getText();
    }
}
