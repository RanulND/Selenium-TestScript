package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public static Properties properties = new Properties();
    private static FileReader fileReader;
    private String configFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

    @BeforeMethod
    public void setUp() throws IOException {
        if (driver == null) {
            fileReader = new FileReader(configFilePath);
            properties.load(fileReader);
        }

        if(properties.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver =  new ChromeDriver();
        } else if (properties.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        System.out.println("Tear Down Successful");
    }
}
