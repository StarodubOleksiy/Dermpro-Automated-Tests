package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Membership extends ProductListContainer {

    @FindBy(xpath = "//span[@itemprop='name']")
    private WebElement membershipName;

    @FindBy(xpath = "//button[@id='product-addtocart-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@data-ui-id='checkout-cart-validationmessages-message-success']")
    private WebElement membershipSuccessMessage;

    public Membership(WebDriver driver) {
        super(driver);
    }

    @Step("Verify you are on Membership page {name}")
    public Membership verifySelectedMembershipPage(String name) {
        wait.until(ExpectedConditions.textToBePresentInElement(membershipName, name));
        return this;
    }

}
