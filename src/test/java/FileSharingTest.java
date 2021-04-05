import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import page.FileListPage;
import page.HomePage;
import page.LoginPage;

public class FileSharingTest extends AbstractTest {
    private final static String EMAIL = "koross@gmail.com";
    private final static String PASSWORD = "password";

    @BeforeClass
    public static void beforeAll() {
        driver.get("http://localhost:8080");
        new LoginPage(driver).attemptLogin(EMAIL, PASSWORD);
    }

    private File createRandomlyNamedFile() {
        Random rand = new Random();
        File newFile = new File("testfile" + rand.nextInt(10000) + ".txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newFile;
    }

    @Test
    public void pageLoadCorrectly() {
        new HomePage(driver).goToFileListPage();
    }

    @Test
    public void uploadFile() {
        FileListPage flPage = new HomePage(driver).goToFileListPage();
        File randomFile = createRandomlyNamedFile();
        flPage.uploadFile(randomFile.getAbsolutePath(), "Test File For You And Me");

        // check if the file has been successfully uploaded
        flPage.getFileByName(randomFile.getName(), true);
        randomFile.delete();
    }
}
