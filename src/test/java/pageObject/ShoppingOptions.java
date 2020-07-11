package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ShoppingOptions extends PageObjectInitializer {

    @FindBy(xpath = "//span[@data-price-type='finalPrice']//span[@class='price']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//span[@class='filter-value']")
    private WebElement nowShoppingByFilter;

    @FindBy(xpath = "//div[@attribute='color']//a")
    private List<WebElement> colorsList;

    @FindBy(xpath = "//div[contains(@class,'filter-actions')]//span[text()='Clear All']")
    private WebElement clearAllNowShoppingByButton;

    @FindBy(xpath = "//div[@id='narrow-by-list']//div[@class='filter-options-title']")
    private List<WebElement> shoppingOptionTitlesList;

    @FindBy(xpath = "//div[@class='filter-options-item allow active']//a")
    private List<WebElement> activeShoppingOptionCategoryList;

    public ShoppingOptions(WebDriver driver) {
        super(driver);
    }

    @Step("Extract USD price")
    private Long extractUSDPrice(String value) {
        Long result = 0l;
        try {
            int startIndex = value.indexOf("$");
            int endIndex = value.indexOf(".");

            String trimmedValue = value.substring(startIndex + 1, endIndex);
            result = Long.valueOf(trimmedValue);
        } catch (Exception e) {
            System.out.println("Invalid price value " + value);
        }
        return result;
    }

    @Step("Get filter price From")
    private Long getFilterPriceFrom() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@attribute='price']//a/span[1]"));
        return extractUSDPrice(prices.get(0).getText());
    }

    @Step("Get filter price To")
    private Long getFilterPriceTo() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@attribute='price']//a/span[2]"));
        return extractUSDPrice(prices.get(0).getText());
    }

    @Step("Verify filtered products are in a price range")
    private ShoppingOptions verifyProductsAreInPriceRange(Long priceFrom, Long priceTo) {
        if (productPrices.size() > 1) {
            waitUntilProductsPageIsLoaded();
            Long price = extractUSDPrice(productPrices.get(0).getText());
            Assert.assertTrue(price >= priceFrom && price <= priceTo, "Product price should be in range: " + priceFrom + " - " + priceTo);
        }
        return this;
    }

    @Step("Verify price filter in Now Shopping By section")
    private ShoppingOptions verifyPriceFilterInNowShoppingBySection(String priceFrom, String priceTo) {
        wait.until(ExpectedConditions.visibilityOf(nowShoppingByFilter));
        Assert.assertEquals(nowShoppingByFilter.getText(), "$" + priceFrom + ".00 - $" + priceTo + ".99");
        return this;
    }

    @Step("Get Color option")
    private String getColorOption() {
        String color;
        color = colorsList.get(0).getAttribute("aria-label");
        return color;
    }

    @Step("Verify color filter in Now Shopping By section")
    private ShoppingOptions verifyColorFilterInNowShoppingBySection(String color) {
        wait.until(ExpectedConditions.visibilityOf(nowShoppingByFilter));
        Assert.assertEquals(nowShoppingByFilter.getText(), color);
        return this;
    }

    @Step("Click to clear Now Shopping by section")
    private ShoppingOptions clickClearAllNowShoppingBySection() {
        click(clearAllNowShoppingByButton);
        waitUntilProductsPageIsLoaded();
        return this;
    }

    @Step("Click on <<Shopping option>> by index {0}")
    private ShoppingOptions clickShoppingOption(int index) {
        click(shoppingOptionTitlesList.get(index));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-options-item allow active']//div[@style='display: block;']")));
        return this;
    }

    @Step("Verify Shopping Option filters in Now Shopping By section")
    public ShoppingOptions verifyShoppingOptionFiltersInNowShoppingBySection() {
        int index;
        for (index = 0; index < shoppingOptionTitlesList.size(); index++) {
            String option = shoppingOptionTitlesList.get(index).getText();
            if (option.equals("Price")) {
                clickShoppingOption(index);
                Long priceFrom = getFilterPriceFrom();
                Long priceTo = getFilterPriceTo();
                click(activeShoppingOptionCategoryList.get(0));
                verifyProductsAreInPriceRange(priceFrom, priceTo);
                verifyPriceFilterInNowShoppingBySection(priceFrom.toString(), priceTo.toString());
                clickClearAllNowShoppingBySection();
            } else if (option.equals("Color")) {
                clickShoppingOption(index);
                click(activeShoppingOptionCategoryList.get(0));
                String color = getColorOption();
                verifyColorFilterInNowShoppingBySection(color);
                clickClearAllNowShoppingBySection();
            } else {
                clickShoppingOption(index);
                String optionText = activeShoppingOptionCategoryList.get(0).getText();
                click(activeShoppingOptionCategoryList.get(0));
                waitUntilProductsPageIsLoaded();
                wait.until(ExpectedConditions.visibilityOf(nowShoppingByFilter));
                Assert.assertTrue(optionText.contains(nowShoppingByFilter.getText()));
                clickClearAllNowShoppingBySection();
            }
        }
        return this;
    }

}
