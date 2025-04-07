package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExtentManager;

import java.util.ArrayList;

public class ProductPage {
    WebDriver driver;

    private By appleStoreLink = By.xpath("//a[contains(text(),'Visit the Apple Store')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            ExtentManager.getTest().info("Switched to new browser tab.");
        } else {
            ExtentManager.getTest().info("Only one tab found. No switch performed.");
        }
    }

    public void clickAppleStoreLink() {
        driver.findElement(appleStoreLink).click();
        ExtentManager.getTest().info("Clicked on 'Visit the Apple Store' link.");
    }
}
