package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

    public WebDriver driver;
    public Properties prop;
    public WebDriver initializeDriver() throws IOException {

        // Fetching properties...
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        String osName = prop.getProperty("os");
        System.out.println(browserName);

        // Set driver according to properties as per system requirements.
        if (browserName.equals("chrome") && osName.equals("mac")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            driver = new ChromeDriver(options);
        }

        if (browserName.equals("chrome") && osName.equals("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            driver = new ChromeDriver(options);
        }

        if (browserName.equals("firefox") && osName.equals("mac")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/resources/geckodriver");
            driver = new FirefoxDriver();
            //execute in firefox driver
        } else if (browserName.equals("firefox") && osName.equals("windows")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            //execute in firefox driver
        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;

    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

        // Takes screenshot of test cases.
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}