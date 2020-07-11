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

public class WishlistLeftSideBar extends PageObjectInitializer {

    @FindBy(xpath = "//div[contains(@class,'block-wishlist')]//span[@class='counter']")
    private WebElement counter;

    @FindBy(xpath = "//li[@class='product-item']//a[@title='Remove This Item']")
    private List<WebElement> removeItemLinks;

    @FindBy(xpath = "//div[contains(@class,'block-wishlist')]//strong[@class='product-item-name']//a")
    private List<WebElement> productNamesInWishList;

    public WishlistLeftSideBar(WebDriver driver) {
        super(driver);
    }

    @Step("Verify product {productName} is present in wishlist leftside bar")
    public WishlistLeftSideBar verifyProductIsPresent(String productName) {
        List<WebElement> products = driver.findElements(By.xpath("//span[@data-bind='text: product_name']"));
        wait.until(ExpectedConditions.visibilityOf(products.get(0)));
        scrollIntoView(products.get(0), driver);
        Assert.assertTrue(products.get(0).getText().contains(productName.toUpperCase()));
        return this;
    }

    @Step("Click on <<Remove>> span")
    public WishlistLeftSideBar clickRemoveItemFromWishlistLink() {
        click(removeItemLinks.get(0));
        return this;
    }

    @Step("Verify items counter is equals to {itemsCount} on wishlist left side bar")
    public WishlistLeftSideBar verifyItemsCounter() {
        wait.until(ExpectedConditions.visibilityOf(counter));
        scrollIntoView(counter, driver);
        String value = new String();
        if (productNamesInWishList.size() == 1)
            value = "1 Item";
        else
            value = String.valueOf(productNamesInWishList.size()) + " Items";
        Assert.assertEquals(counter.getText(), value);
        return this;
    }


    @Step("Remove all items")
    public WishlistLeftSideBar removeAllItems() {
        if (removeItemLinks.size() > 0) {
            wait.until(ExpectedConditions.visibilityOf(removeItemLinks.get(0)));
            scrollIntoView(removeItemLinks.get(0), driver);
            removeItemLinks.forEach(link ->
                    {
                        click(removeItemLinks.get(0));
                    }
            );
        }
        return this;
    }

    @Step("Verify wish list is not empty")
    public boolean verifyWishListIsNotEmpty() {
        return removeItemLinks.size() > 0;
    }

}
