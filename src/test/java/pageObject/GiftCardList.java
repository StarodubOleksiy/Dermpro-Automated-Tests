package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class GiftCardList extends ProductListContainer {

    @FindBy(xpath = "//div[@class='category-description']//h1")
    private WebElement eGiftCardTitle;

    @FindBy(xpath = "//a[@class='product-item-link']")
    private List<WebElement> giftCardNames;

    @FindBy(xpath = "//span[@data-price-type='minPrice']")
    private List<WebElement> pricesFrom;

    @FindBy(xpath = "//span[@data-price-type='maxPrice']")
    private List<WebElement> pricesTo;

    public GiftCardList(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until cart page is loaded")
    public GiftCardList waitUntilGiftCardListPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(eGiftCardTitle));
        return this;
    }

    @Step("Verify if you on Gift Card list page")
    public GiftCardList verifyGiftCardListPage(String pageName) {
        scrollIntoView(eGiftCardTitle, driver);
        verifyTitle(eGiftCardTitle, pageName);
        return this;
    }

    @Step("Verify if Gift Card name and price are present on the page {cardName} {priceFrom} {priceTo}")
    public GiftCardList verifyIfGiftCardNameAndPricesArePresent(String cardName, String priceFrom, String priceTo) {
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", cardName), driver), "Gift card with " + cardName + " name should be displayed");
        Assert.assertTrue(isElementPresent(String.format("//span[contains(text(),'%s')]", priceFrom), driver), "Gift card with " + priceFrom + " price should be displayed");
        Assert.assertTrue(isElementPresent(String.format("//span[contains(text(),'%s')]", priceTo), driver), "Gift card with " + priceTo + " price should be displayed");
        return this;
    }


}
