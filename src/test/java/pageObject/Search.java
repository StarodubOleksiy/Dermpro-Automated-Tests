package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Search extends PageObjectInitializer {

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
    private WebElement searchResultPageTitle;

    @FindBy(xpath = "//main[@id='maincontent']//div[contains(text(),'Your search returned no results.')]")
    private WebElement searchReturnNoResult;

    @FindBy(xpath = "//div[@id='block-search']//span[@data-bind='text: num_results']")
    private WebElement numberOfSearchMatches;

    @FindBy(xpath = "//p[@id='toolbar-amount']")
    private WebElement itemsSearched;

    public Search(WebDriver driver) {
        super(driver);
    }


    @Step("Verify Search result page with item {item}")
    public Search verifyItemInSearchResultPageTitle(String item) {
        verifyTitle(searchResultPageTitle, String.format("Search results for: '%s'",item));
        return this;
    }

    @Step("Search return page with no result")
    public Search verifySearchNoResult() {
        Assert.assertTrue(searchReturnNoResult.isDisplayed(), "Empty result should be returned");
        return this;
    }

    @Step("Search return page with valid result {productTitle} {itemsNumber}")
    public Search verifySearchResult(String productTitle, String itemsNumber) {
        WebElement productWebElement = driver.findElement(By.xpath(String.format("//div[@class='search results']//a[contains(@title,'%s')]", productTitle)));
        Assert.assertTrue(productWebElement.isDisplayed(), productTitle + " product should be displayed");
        Assert.assertEquals(itemsSearched.getText(), itemsNumber);
        return this;
    }

    @Step("Search return number of matches")
    public Search verifyMatchesAmount() {
        Assert.assertTrue(isElementPresent("//div[@id='block-search']//span[@data-bind='text: num_results']", driver));
        return this;
    }

}
