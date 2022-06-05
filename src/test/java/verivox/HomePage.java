package verivox;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;

public class HomePage extends base {
    public WebDriver driver;
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
        System.out.println("homepage driver initialised");
    }

    @Test(priority = 0)
    public void verifyTheDslCalculator() throws IOException, InterruptedException {

        // Executing Scenario 1...
        WebDriverWait wait = new WebDriverWait(driver, 90L);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        LandingPage l = new LandingPage(driver);

        // Accepting Cookies
        Thread.sleep(3000); // TO DO  - Change later with Explicit/fluent wait
        if (l.cookieAccept() != null || l.cookieAcceptEnglish() != null) {
            l.cookieAccept().click();
        }
        log.info("Cookie has been accepted");

        // Entering inputs to check available tariffs.
        l.clickDsl().click();
        wait.until(ExpectedConditions.visibilityOf(l.inputCode()));
        l.inputCode().sendKeys(prop.getProperty("pin"));
        log.info("Selected 030 and 16Mbit/s from the input fields");
        wait.until(ExpectedConditions.visibilityOf(l.compareButton()));
        l.compareButton().click();
        log.info("Clicked on Compare Button");

        // Getting list of available products on first page; Also verifying if there are at least 5 products displayed on screen.
        ArrayList < WebElement > list = (ArrayList < WebElement > ) l.listOfProducts();
        int size = list.size();
        boolean value = size > 0;
        boolean tariffs = size >= 5;
        assertTrue(value);
        log.info("The list of Triffs has been displayed");
        assertTrue(tariffs);
        log.info("at least 5 internet tariffs are displayed");

        // Check if there are at least 5 products whose speed is >= 100 Mbit/s
        ArrayList < WebElement > speeds = (ArrayList < WebElement > ) l.speedsOfTariffs();
        int count = 0;
        int downloadSpeed;
        String speed;
        for (int i = 0; i < speeds.size(); i++) {
            speed = speeds.get(i).getText();
            downloadSpeed = Integer.parseInt(speed);
            if (downloadSpeed >= 100) {
                count = count + 1;
            }
        }
        boolean speedChecks = (count >= 5);
        Assert.assertTrue(speedChecks, "There are atleast 5 tariffs in the list whose speed is >= 100 Mbit/s");
        log.info("the displayed tariffs provide at least 100 Mbit/s download speed");

        // Scenario 1 completed with all the requirements(mentioned in the assignment).
    }

    @Test(dependsOnMethods = {
        "verifyTheDslCalculator"
    })
    public void loadMultipleTariffResultPages() throws IOException, InterruptedException {

        // Executing Scenario 2...
        LandingPage l = new LandingPage(driver);
        String text = l.getNumberOfTariffs().getText();
        WebElement element = driver.findElement(By.xpath("//button[@class='btn btn-primary text-uppercase ng-star-inserted']"));
        String[] words = text.split("\\s");
        int totalTarife = Integer.parseInt(words[0]);
        int productsCount;
        ArrayList < WebElement > products;

        // Initialise the remaningCount variable which stores the non-displayed products count.
        int remainingCount;
        if (totalTarife < 20) {
            remainingCount = 0;
        } else {
            remainingCount = totalTarife - 20;
        }

        // Loop till non-displayed products are less than 20;
        while (remainingCount >= 20) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", l.element());
            Thread.sleep(2000); // TO DO  - Change later with Explicit/fluent wait
            l.clickMoreResults().click();
            Thread.sleep(3000); // TO DO  - Change later with Explicit/fluent wait
            remainingCount = remainingCount - 20;
        }

        // Test case to confirm whether text displayed on the button is right when products remains less than 20;
        if (remainingCount > 0 && remainingCount < 20) {
            String buttonText = l.element().getText();
            int buttonCount = Integer.parseInt(buttonText.split("\\s")[0]);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            assertEquals(buttonCount, remainingCount);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", l.element());
            log.info("Verify that the final weitere Tarife laden button displays the expected number of tariffs remaining");
            l.clickMoreResults().click();
        }

        // Test case to verify that the total number of tariffs displayed matches the total listed in the Ermittelte Tarife section.
        Thread.sleep(2000); // TO DO  - Change later with Explicit/fluent wait
        products = (ArrayList < WebElement > ) l.listOfProducts();
        productsCount = products.size();
        assertEquals(productsCount, totalTarife);
        log.info("The total number of tariffs displayed matches the total listed in the Ermittelte Tarife section");

        // Test case to confirm the weitere Tarife laden button is no longer displayed when all the tariffs are visible
        WebDriverWait wait = new WebDriverWait(driver, 90L);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(element)));
        log.info("the weitere Tarife laden button is no longer displayed when all the tariffs are visible");

        // Scenario 2 completed with all the requirements(mentioned in the assignment).
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}