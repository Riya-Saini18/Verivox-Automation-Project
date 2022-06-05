package pageObjects;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;

	By dsl = By.xpath("//div[@class='mps-tab-list'] //span[text()='DSL']");
	By code = By.xpath("//input[@name= 'phonePrefix']");
	By bandwidth = By.xpath("//div[@class= 'calc-toggles toggle-two-lines']/label[1]");
	By compare = By.xpath("//div[@class= 'calc-toggles toggle-two-lines']//following-sibling::button");
	By list = By.xpath("//div[@class= 'row my-4 ng-star-inserted']");
	By cookie = By.xpath("//button[text() = 'Alles Akzeptieren']");
	By cookieEnglish = By.xpath("//font[text() = 'Accept everything']");
	By accept = By.xpath("//div[@class= 'calc-toggles toggle-two-lines']//following-sibling::button");
	By speed = By.xpath("//div[contains(@class,'row align-items-center align-items-stretch tariff-wrapper py-sm-2')]//img[@class = 'download-icon']/following-sibling::b");
	By results = By.xpath("//button[@class='btn btn-primary text-uppercase ng-star-inserted']");
	By number= By.xpath("//div[@class = 'text-sm-left flex-row align-items-baseline justify-content-between justify-content-sm-start px-0 d-flex col-12 col-sm-6 mb-2 mb-sm-0']//h2[@class='summary-tariff']");
	By container = By.xpath("//div[contains(@class,'row align-items-center align-items-stretch tariff-wrapper py-sm-2')]");
	By element = By.xpath("//button[@class='btn btn-primary text-uppercase ng-star-inserted']");

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public WebElement clickDsl()
	{
		return driver.findElement(dsl);
	}

	public WebElement inputCode()
	{
		return driver.findElement(code);
	}

	public WebElement bandwidthSelection()
	{
		return driver.findElement(bandwidth);
	}

	public WebElement compareButton()
	{
		return driver.findElement(compare);
	}

	public WebElement listOfElements()
	{
		return driver.findElement(list);
	}

	public WebElement cookieAccept()
	{
		return driver.findElement(cookie);
	}

	public WebElement cookieAcceptEnglish()
	{
		return driver.findElement(cookieEnglish);
	}

	public WebElement waitingToAccept()
	{
		return driver.findElement(accept);
	}

	public WebElement speedCheck()
	{
		return driver.findElement(speed);
	}

	public ArrayList speedsOfTariffs()
	{
		return  (ArrayList) driver.findElements(speed);
	}

	public WebElement clickMoreResults()
	{
		return driver.findElement(results);
	}

	public WebElement getNumberOfTariffs()
	{
		return driver.findElement(number);
	}

	public WebElement container()
	{
		return driver.findElement(container);
	}

	public ArrayList listOfProducts()
	{
		return (ArrayList) driver.findElements(container);
	}

	public WebElement element()
	{
		return driver.findElement(element);
	}
}
