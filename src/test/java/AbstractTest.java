import com.microsoft.edge.seleniumtools.EdgeDriver;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

public abstract class AbstractTest {
    protected static WebDriver driver;

    private static String browserName;

    @BeforeClass
    public static void beforeALl() throws IOException {
        System.out.println("helo from abstract");
        PropertyReader propertyReader = new PropertyReader("src/config.properties");
        browserName = propertyReader.getProp("browser");

        switch (browserName) {
            case "edge" -> {
                System.out.println("edge config found");
                driver = new EdgeDriver();
            }
            case "chrome" -> {
                System.out.println("chrome config found");
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                System.out.println("firefox config found");
                driver = new FirefoxDriver();
            }
            case "ie" -> {
                System.out.println("ie config found");
                driver = new InternetExplorerDriver();
            }
            case "opera" -> {
                System.out.println("opera config found");
                driver = new OperaDriver();
            }
            case "safari" -> {
                System.out.println("safari config found");
                driver = new SafariDriver();
            }
            case "edgelegacy" -> {
                System.out.println("edge legacy config found");
                driver = new org.openqa.selenium.edge.EdgeDriver();
            }
            default -> System.out.println("No browser config found");
        }
    }

    @AfterClass
    public static void afterAll() {
        System.out.println("goodbye from abstract");
        driver.quit();
    }

}
