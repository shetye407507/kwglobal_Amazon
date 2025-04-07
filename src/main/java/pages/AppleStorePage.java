package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentManager;

public class AppleStorePage {
    WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    private By watchDropdown = By.xpath("//span[text()='Apple Watch']/parent::a");
    private By watchOption = By.linkText("Apple Watch SE (GPS + Cellular)");
    private By watchImage = By.xpath("(//span[@class='Price__whole__mQGs5'])[1]");
    private By quickLookModal = By.xpath("//span[contains(text(),'Quick look')]");
    private By quickLookProductTitle = By.xpath("//*[@id='asin-title']/a");

    public AppleStorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectWatchVariant() {
        driver.findElement(watchDropdown).click();
        ExtentManager.getTest().info("Clicked on Apple Watch dropdown.");

        driver.findElement(watchOption).click();
        ExtentManager.getTest().info("Selected 'Apple Watch SE (GPS + Cellular)' from dropdown.");
    }

    public boolean hoverAndCheckQuickLook() {
    	boolean isDisplayed = false;
    	try {
    		
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			// Scroll to the element first using JavaScript
			WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(watchImage));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", image);
			
			// Wait for slight time after scroll (DOM settle time)
			Thread.sleep(500);
			// Hover using Actions
			Actions actions = new Actions(driver);
			actions.scrollToElement(image);
			ExtentManager.getTest().info("Scrolled to Apple Watch image.");
			actions.moveToElement(image).build().perform();
			ExtentManager.getTest().info("Hovered over Apple Watch image.");

			// Wait for Quick Look modal to appear
			wait.until(ExpectedConditions.visibilityOfElementLocated(quickLookModal));
			isDisplayed = driver.findElement(quickLookModal).isDisplayed();
			ExtentManager.getTest().info("Quick Look modal displayed: " + isDisplayed);
			driver.findElement(quickLookModal).click();
			ExtentManager.getTest().info("Clicked on Quick view modal" );
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	
		return isDisplayed;
    }
    

    public String getQuickLookModalTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quickLookProductTitle));
        return titleElement.getText().trim();
    }

}