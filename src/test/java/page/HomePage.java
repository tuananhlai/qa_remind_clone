package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    protected WebDriver driver;
    private By sideBarBy = By.className("v-navigation-drawer__content");

    public HomePage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(sideBarBy));
    }

    /**
     * Go to the first conversation of the first class.
     * 
     * @return
     */
    public SendMessagePage goToFirstClassConvo() {
        WebElement firstClassItem = driver.findElement(By.className("itemClass"));
        firstClassItem.click();
        WebElement messageTab = driver.findElement(By.className("tabMessage"));
        messageTab.click();
        WebElement firstConversation = driver.findElement(By.className("itemConversation"));
        firstConversation.click();

        return new SendMessagePage(driver);
    }

    public FileListPage goToFileListPage() {
        WebElement firstClassItem = driver.findElement(By.className("itemClass"));
        firstClassItem.click();
        WebElement fileTab = driver.findElement(By.className("tabFile"));
        fileTab.click();

        return new FileListPage(driver);
    }
}
