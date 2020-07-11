package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AdminOrderDetails extends AdminOrders{

    @FindBy(xpath = "//button[@id='order_ship']")
    private WebElement shipButton;

    @FindBy(xpath = "//button[@title='Submit Shipment']")
    private WebElement submitShipmentButton;

    @FindBy(xpath = "//div[@class='message message-success success']")
    private WebElement successMessage;

    public AdminOrderDetails(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Ship button")
    public AdminOrderDetails clickShipButton() {
        clickAdmin(shipButton);
        return this;
    }

    @Step("Click on Submit Shipment button")
    public AdminOrderDetails clickSubmitShipmentButton() {
        scrollIntoView(submitShipmentButton, driver);
        jsClick(submitShipmentButton, driver);
        return this;
    }

    @Step("Verify message that Shipment created successfully")
    public AdminOrderDetails verifySuccessShipmentMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertEquals(successMessage.getText(), "The shipment has been created.");
        return this;
    }

}
