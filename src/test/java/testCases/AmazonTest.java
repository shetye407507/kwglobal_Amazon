package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.AppleStorePage;
import pages.ProductPage;
import utils.ExtentManager;

import java.util.List;

public class AmazonTest extends BaseTest {

    /**
     * Validates that all search suggestions for 'iPhone 13' are relevant.
     */
    @Test(priority = 1, description = "Validate that all suggestions returned for 'iPhone 13' search are relevant.")
    public void validateSearchSuggestions() {
        ExtentManager.getTest().info("Test Started: validateSearchSuggestions");

        AmazonHomePage home = new AmazonHomePage(driver);
        home.selectElectronicsFromCategoryDropdown();
        home.searchProduct("iPhone 13");
        ExtentManager.getTest().info("Searching for 'iPhone 13'");

        List<String> suggestions = home.getSuggestionsText();
        ExtentManager.getTest().info("Retrieved " + suggestions.size() + " suggestions.");

        for (String s : suggestions) {
            ExtentManager.getTest().info("Validating suggestion: " + s);
            Assert.assertTrue(s.contains("iphone 13"), "Suggestion not relevant: " + s);
        }

        ExtentManager.getTest().pass("All suggestions are related to 'iPhone 13'");
    }

    /**
     * Selects a specific product variant from the dropdown suggestions.
     */
    @Test(priority = 2, description = "Search for 'iphone 13 128gb' and select exact match from suggestions.")
    public void selectProductFromDropdown() {
        ExtentManager.getTest().info("Test Started: selectProductFromDropdown");

        AmazonHomePage home = new AmazonHomePage(driver);
        home.searchProduct("iphone 13 128gb");
        ExtentManager.getTest().info("Searching for 'iphone 13 128gb'");

        home.selectSuggestionByText("iphone 13 128gb");
        ExtentManager.getTest().pass("Selected product variant from dropdown suggestions.");

        home.clickOnIphoneTag();
    }

    /**
     * Switches to a new tab and clicks on 'Visit the Apple Store' link.
     */
    @Test(priority = 3, description = "Switch to product tab and verify navigation to Apple Store link.")
    public void verifyNewTabAndAppleStoreNavigation() {
        ExtentManager.getTest().info("Test Started: verifyNewTabAndAppleStoreNavigation");

        ProductPage product = new ProductPage(driver);
        product.switchToNewTab();
        ExtentManager.getTest().info("Switched to new tab.");

        product.clickAppleStoreLink();
        ExtentManager.getTest().pass("Clicked on 'Visit the Apple Store' link.");
    }

    /**
     * Verifies the Quick Look modal for an Apple Watch variant after hover action.
     */
    @Test(priority = 4, description = "Hover over Apple Watch and verify if 'Quick Look' modal appears with correct title.")
    public void verifyAppleWatchQuickLook() {
        ExtentManager.getTest().info("Test Started: verifyAppleWatchQuickLook");

        AppleStorePage store = new AppleStorePage(driver);
        store.selectWatchVariant();
        ExtentManager.getTest().info("Selected Apple Watch variant.");

        boolean quickLookVisible = store.hoverAndCheckQuickLook();
        ExtentManager.getTest().info("Quick Look visibility: " + quickLookVisible);

        Assert.assertTrue(quickLookVisible, "Quick Look modal not displayed");
        ExtentManager.getTest().pass("Quick Look modal displayed as expected.");

        String modalTitle = store.getQuickLookModalTitle();
        ExtentManager.getTest().info("Quick Look modal product title: " + modalTitle);

        Assert.assertTrue(modalTitle.contains("Apple Watch SE"), "Modal title does not match hovered product.");
        ExtentManager.getTest().pass("Quick Look modal content matches the hovered product.");
    }
}
