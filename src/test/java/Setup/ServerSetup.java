package Setup;

import Utility.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class ServerSetup {
    public static AndroidDriver driver;
    public static PropertiesLoader properties;

    @Before
    public void setUp() throws IOException {
        String propertiesPath = "src/test/resources/TestData/testData.properties";
        properties = new PropertiesLoader(propertiesPath);
        String capabilitiesPath = "src/test/resources/Capabilities/capabilities.json";

        CapabilitiesSetup loader = new CapabilitiesSetup();
        DesiredCapabilities capabilities = loader.loadCapabilities(capabilitiesPath);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        System.out.println("Driver initialized: " + driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
