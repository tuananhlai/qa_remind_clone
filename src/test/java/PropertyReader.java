import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties prop;

    public PropertyReader(String path) throws IOException {
        FileInputStream ip = new FileInputStream(path);
        prop = new Properties();
        prop.load(ip);
    }

    public String getProp(String name) {
        return prop.getProperty(name);
    }
}
