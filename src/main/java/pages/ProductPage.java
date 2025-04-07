package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ExtentManager;
import java.util.ArrayList;

public class ProductPage {
    WebDriver driver;

    private By appleStoreLink = By.xpath("//a[contains(text(),'Visit the Apple Store')]");

    /**
     * Constructor to initialize the WebDriver instance for this page.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Switches the WebDriver's focus to the second browser tab, if present.
     */
    public void switchToNewTab() {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.get(1));
                ExtentManager.getTest().info("Switched to new browser tab.");
            } else {
                ExtentManager.getTest().info("Only one tab found. No switch performed.");
            }
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error while switching to new tab: " + e.getMessage());
        }
    }

    /**
     * Clicks on the "Visit the Apple Store" link present on the product page.
     */
    public void clickAppleStoreLink() {
        try {
            driver.findElement(appleStoreLink).click();
            ExtentManager.getTest().info("Clicked on 'Visit the Apple Store' link.");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Failed to click on Apple Store link: " + e.getMessage());
        }
    }
}
