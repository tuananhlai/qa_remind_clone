import com.microsoft.edge.seleniumtools.EdgeDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractTest {
    protected static WebDriver driver;

    private static String browserName;

    @BeforeClass
    public static void beforeAbstractTest() throws Exception {
        System.out.println("helo from abstract");
        DesiredCapabilities capability = DesiredCapabilities.chrome();

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    }

    @AfterClass
    public static void afterAbstractTest() {
        System.out.println("goodbye from abstract");
        driver.quit();
    }

}
