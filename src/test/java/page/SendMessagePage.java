package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessagePage {
    protected WebDriver driver;

    private By textBoxBy = By.id("message-text-box-input");
    private By sendBtnBy = By.id("btnSend");
    private By messageListBy = By.id("message-list-item");
    private By itemMessageBy = By.className("itemMessage");

    public SendMessagePage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(messageListBy));
    }

    public void sendMessage(String newMessage) {
        WebElement messageBoxInput = driver.findElement(textBoxBy);
        messageBoxInput.sendKeys(newMessage);
        WebElement sendBtn = driver.findElement(sendBtnBy);
        sendBtn.click();
    }

    public String getLastMessage() {
        WebElement lastMessage = driver.findElement(By.xpath("(//*[contains(@class, 'itemMessage')])[last()]"));
        return lastMessage.findElement(By.tagName("p")).getText();
    }
}
