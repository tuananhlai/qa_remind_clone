package page;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileListPage {
    protected WebDriver driver;
    private By fileListBy = By.id("file-list");
    private By fileAddBtnBy = By.id("btn-add-file");
    private By fileDialogInputBy = By.id("file-input");
    private By fileDialogMsgBy = By.id("txt-file-msg");
    private By fileUploadBtnBy = By.id("btn-file-upload");
    private By dialogCloseBtnBy = By.id("btn-close");

    public FileListPage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(fileListBy));
    }

    public void uploadFile(String filePath, String message) {
        driver.findElement(fileAddBtnBy).click();
        driver.findElement(fileDialogMsgBy).sendKeys(message);
        driver.findElement(fileDialogInputBy).sendKeys(filePath);
        WebElement uploadBtn = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(fileUploadBtnBy));
        uploadBtn.click();
        driver.findElement(dialogCloseBtnBy).click();
    }

    public WebElement getFileByName(String name, boolean wait) throws NoSuchElementException {
        String xpath = String.format(
                "//div[contains(@class, 'file-list-item')]//div[contains(@class, 'v-list-item__title') and text()='%s']",
                name);
        if (wait) {
            return new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }
        return driver.findElement(By.xpath(xpath));
    }

}
