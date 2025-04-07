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

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void selectProductFromDropdown() {
        ExtentManager.getTest().info("Test Started: selectProductFromDropdown");

        AmazonHomePage home = new AmazonHomePage(driver);
        home.searchProduct("iphone 13 128gb");
        ExtentManager.getTest().info("Searching for 'iphone 13 128gb'");

        home.selectSuggestionByText("iphone 13 128gb");
        ExtentManager.getTest().pass("Selected product variant from dropdown suggestions.");
        
        home.clickOnIphoneTag();
        
    }

    @Test(priority = 3)
    public void verifyNewTabAndAppleStoreNavigation() {
        ExtentManager.getTest().info("Test Started: verifyNewTabAndAppleStoreNavigation");

        ProductPage product = new ProductPage(driver);
        product.switchToNewTab();
        ExtentManager.getTest().info("Switched to new tab.");

        product.clickAppleStoreLink();
        ExtentManager.getTest().pass("Clicked on 'Visit the Apple Store' link.");
    }

    @Test(priority = 4)
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

        Assert.assertTrue(modalTitle.contains("Apple Watch SE") ,"Modal title does not match hovered product.");
        ExtentManager.getTest().pass("Quick Look modal content matches the hovered product.");
    }
}