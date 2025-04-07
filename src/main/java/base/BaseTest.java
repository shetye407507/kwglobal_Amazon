package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    // This method runs once before any test methods in the class
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        int waitTime = Integer.parseInt(ConfigReader.get("implicitWait"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); 
        options.addArguments("--disable-popup-blocking"); 
        options.addArguments("start-maximized");         

        // Initialize the WebDriver with Chrome and the specified options
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));

        driver.get(ConfigReader.get("baseUrl"));
    }

    // This method runs once after all test methods in the class have executed
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
