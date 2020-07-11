package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdvancedSearch extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement productNameInput;

    @FindBy(xpath = "//input[@id='sku']")
    private WebElement skuInput;

    @FindBy(xpath = "//input[@id='description']")
    private WebElement descriptionInput;

    @FindBy(xpath = "//input[@id='short_description']")
    private WebElement shortDescriptionInput;

    @FindBy(xpath = "//input[@id='price']")
    private WebElement priceFromInput;

    @FindBy(xpath = "//input[@id='price_to']")
    private WebElement priceToInput;

    @FindBy(xpath = "//div[@class='actions-toolbar']//button[@title='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='page messages']//div[text()='Please specify at least one search term.']")
    private WebElement emptySearchAlert;

    @FindBy(xpath = "//div[@class='message error']//div[contains(text(),'any items matching these search criteria.')]")
    private WebElement invalidSearchAlert;

    @FindBy(xpath = "//main[@id='maincontent']//div[@class='search found']//strong")
    private WebElement advancedItemsSearched;

    @FindBy(xpath = "//a[text()='Modify your search.']")
    private WebElement modifyYourSearchLink;

    public AdvancedSearch(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Modify your search>> link")
    public AdvancedSearch clickModifyYourSearchLink() {
        click(modifyYourSearchLink);
        return this;
    }

    @Step("Click on <<Search>> button")
    public AdvancedSearch clickSearchButton() {
        click(searchButton);
        return this;
    }

    @Step("Enter Product name {productName}")
    public AdvancedSearch enterProductName(String productName) {
        sendText(productNameInput, productName);
        return this;
    }

    @Step("Enter Price from {priceFrom}")
    public AdvancedSearch enterPriceFrom(String priceFrom) {
        sendText(priceFromInput, priceFrom);
        return this;
    }

    @Step("Enter Price to {priceTo}")
    public AdvancedSearch enterPriceTo(String priceTo) {
        sendText(priceToInput, priceTo);
        return this;
    }

    @Step("Enter Description {description}")
    public AdvancedSearch enterDescription(String description) {
        sendText(descriptionInput, description);
        return this;
    }

    @Step("Enter Description {short description}")
    public AdvancedSearch enterShortDescription(String description) {
        sendText(shortDescriptionInput, description);
        return this;
    }

    @Step("Verify empty result search")
    public AdvancedSearch verifyEmptyResultSearch() {
        Assert.assertTrue(emptySearchAlert.isDisplayed());
        return this;
    }

    @Step("Verify advanced search result {productTitle} {itemsNumber}")
    public AdvancedSearch verifyAdvancedSearchResult(String productTitle, String itemsNumber) {
        WebElement productWebElement = driver.findElement(By.xpath(String.format("//div[@class='search results']//a[contains(@title,'%s')]", productTitle)));
        Assert.assertTrue(productWebElement.isDisplayed());
        Assert.assertEquals(advancedItemsSearched.getText(), itemsNumber);
        return this;
    }

    @Step("Verify advanced search result {productTitle}")
    public AdvancedSearch verifyAdvancedSearchResultItemsPresent(String productTitle) {
        WebElement productWebElement = driver.findElement(By.xpath(String.format("//div[@class='search results']//a[contains(@title,'%s')]", productTitle)));
        Assert.assertTrue(productWebElement.isDisplayed());
        Assert.assertTrue(advancedItemsSearched.isDisplayed());
        return this;
    }


    @Step("Verify invalid advanced search")
    public AdvancedSearch verifyInvalidAdvancedSearch() {
        Assert.assertTrue(invalidSearchAlert.isDisplayed());
        return this;
    }

    @Step("Verify if can't find any items matching these search criteria: {productName}, {description}, {shortDescription}, {minPrice} ,{maxPrice}")
    public AdvancedSearch verifyMatchingSearchCriteria(String productName, String description, String shortDescription, String minPrice, String maxPrice) {
        Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", productName), driver));
        if (description.length() > 128)
            Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", description.substring(0, 128)), driver));
        else
            Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", description), driver));
        Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", shortDescription), driver));
        Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", minPrice), driver));
        Assert.assertTrue(isElementPresent(String.format("//li[contains(text(),'%s')]", maxPrice), driver));
        return this;
    }

    @Step("Verify if you on <<Advanced Search>> page")
    public AdvancedSearch verifyIfYouOnAdvancedSearchPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Advanced Search");
        return this;
    }
}
