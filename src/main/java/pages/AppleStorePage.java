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

    // Locators for Apple Watch section
    private By watchDropdown = By.xpath("//span[text()='Apple Watch']/parent::a");
    private By watchOption = By.linkText("Apple Watch SE (GPS + Cellular)");
    private By watchImage = By.xpath("(//span[@class='Price__whole__mQGs5'])[1]");
    private By quickLookModal = By.xpath("//span[contains(text(),'Quick look')]");
    private By quickLookProductTitle = By.xpath("//*[@id='asin-title']/a");

    // Constructor to initialize WebDriver
    public AppleStorePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks on the Apple Watch dropdown and selects the "SE (GPS + Cellular)" variant.
     */
    public void selectWatchVariant() {
        try {
            driver.findElement(watchDropdown).click();
            ExtentManager.getTest().info("Clicked on Apple Watch dropdown.");

            driver.findElement(watchOption).click();
            ExtentManager.getTest().info("Selected 'Apple Watch SE (GPS + Cellular)' from dropdown.");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error while selecting watch variant: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Hovers over the product image to trigger the "Quick Look" modal and clicks on it.
     * @return true if the Quick Look modal appears, false otherwise.
     */
    public boolean hoverAndCheckQuickLook() {
        boolean isDisplayed = false;
        try {
            Thread.sleep(500); // Small wait to let DOM load

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(watchImage));

            // Hover over product image
            Actions actions = new Actions(driver);
            actions.scrollToElement(image);
            ExtentManager.getTest().info("Scrolled to Apple Watch image.");
            actions.moveToElement(image).build().perform();
            ExtentManager.getTest().info("Hovered over Apple Watch image.");

            // Wait for "Quick Look" modal to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(quickLookModal));
            isDisplayed = driver.findElement(quickLookModal).isDisplayed();
            ExtentManager.getTest().info("Quick Look modal displayed: " + isDisplayed);

            // Click on the modal
            driver.findElement(quickLookModal).click();
            ExtentManager.getTest().info("Clicked on Quick Look modal.");

        } catch (InterruptedException e) {
            ExtentManager.getTest().fail("Thread interrupted while hovering: " + e.getMessage());
            Thread.currentThread().interrupt(); // Reset interrupt flag
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error while hovering/checking Quick Look: " + e.getMessage());
            e.printStackTrace();
        }
        return isDisplayed;
    }

    /**
     * Gets the product title from the "Quick Look" modal.
     * @return the trimmed text of the product title.
     */
    public String getQuickLookModalTitle() {
        String title = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quickLookProductTitle));
            title = titleElement.getText().trim();
            ExtentManager.getTest().info("Captured Quick Look modal title: " + title);
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to get product title from Quick Look modal: " + e.getMessage());
            e.printStackTrace();
        }
        return title;
    }
}
