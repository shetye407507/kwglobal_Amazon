package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentManager;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AmazonHomePage {
    WebDriver driver;

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchSuggestions = By.xpath("//div[@class='s-suggestion s-suggestion-ellipsis-direction']");
    private By categorySearchDropdown = By.id("searchDropdownBox");
    private By iphone13Label = By.xpath("//span[starts-with(text(),'Apple iPhone 13 (128GB)')]");
    private By alternateiphone13Label = By.xpath("//span[starts-with(text(),'Apple iPhone 13')]");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectElectronicsFromCategoryDropdown() {
        Select categoryDropdown = new Select(driver.findElement(categorySearchDropdown));
        categoryDropdown.selectByValue("search-alias=electronics");
        ExtentManager.getTest().info("Select Electronics as Option from Category dropdown");
    }

    public void searchProduct(String query) {
        try {
			WebElement input = driver.findElement(searchBox);
			input.clear();
			ExtentManager.getTest().info("Cleared search box.");
			input.sendKeys(query);
			ExtentManager.getTest().info("Entered search term: " + query);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			ExtentManager.getTest().fail("Error while fetching searching product: " + e.getMessage());
		}
    }

    public List<String> getSuggestionsText() {
        List<String> texts = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestions));
            List<WebElement> elements = driver.findElements(searchSuggestions);

            for (WebElement el : elements) {
                try {
                    String suggestion = el.getText().toLowerCase();
                    texts.add(suggestion);
                    ExtentManager.getTest().info("Captured suggestion: " + suggestion);
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    ExtentManager.getTest().warning("Stale element skipped while fetching suggestion.");
                }
            }
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error while fetching suggestions: " + e.getMessage());
        }

        ExtentManager.getTest().info("Total suggestions captured: " + texts.size());
        return texts;
    }

    public void submitSearchWithEnter() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
        ExtentManager.getTest().info("Pressed ENTER key to submit search.");
    }

    public void clickOnIphoneTag() {
        try {
			driver.findElement(iphone13Label).click();
			ExtentManager.getTest().info("Clicked on Iphone Label");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			driver.findElement(alternateiphone13Label).click();
			ExtentManager.getTest().info("Clicked on altrernate Iphone Label");
		}
    }

    public void selectSuggestionByText(String suggestionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
        	
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestions));
            List<WebElement> elements = driver.findElements(searchSuggestions);
            for (WebElement el : elements) {
                try {
                    if (el.getText().equalsIgnoreCase(suggestionText)) {
                        wait.until(ExpectedConditions.elementToBeClickable(el));
                        el.click();
                        ExtentManager.getTest().info("Selected suggestion: " + suggestionText);
                        return;
                    }
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    ExtentManager.getTest().warning("Stale element skipped during suggestion selection."+ el.getText());
                }
            }
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error while selecting suggestion: " + e.getMessage());
        }
        ExtentManager.getTest().info("Suggestion not found: " + suggestionText);
    }
}
