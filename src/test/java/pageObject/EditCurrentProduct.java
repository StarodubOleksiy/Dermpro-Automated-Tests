package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditCurrentProduct extends PageObjectInitializer {


    @FindBy(xpath = "//button[@id='product-updatecart-button']")
    private WebElement updateCart;

    @FindBy(xpath = "//div[@class='gallery-placeholder']")
    private WebElement galleryPlaceholder;

    public EditCurrentProduct(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Update>> button on <<Edit>> page")
    public EditCurrentProduct clickUpdateCartButton() {
        click(updateCart);
        return this;
    }

    @Step("Wait until edit current product page is loaded")
    public EditCurrentProduct waitUntilEditCurrentProductPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(galleryPlaceholder));
        return this;
    }


}
