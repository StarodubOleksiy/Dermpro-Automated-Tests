package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ViewOrder extends PageObjectInitializer {

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
    private WebElement orderId;

    @FindBy(xpath = "//span[@class='order-status']")
    private WebElement orderStatus;

    @FindBy(xpath = "//a[@class='action back']")
    private WebElement backToMyOrdersButton;

    // Items Information

    @FindBy(xpath = "//div[@class='order-date']")
    private WebElement orderDate;

     @FindBy(xpath = "//li/a[text()='Invoices']")
    private WebElement invoicesTab;

    @FindBy(xpath = "//a[@class='action order']/span[text()='Reorder']")
    private WebElement reorderButton;

    @FindBy(xpath = "//a[@class='action print']/span[text()='Print Order']")
    private WebElement printOrderButton;

    @FindBy(xpath = "//div[@class='order-title']/strong")
    private WebElement orderedItemsTitle;

    @FindBy(xpath = "//td[@class='col name']/strong")
    private WebElement productName;

    @FindBy(xpath = "//td[@class='col sku']")
    private WebElement productSku;

    @FindBy(xpath = "//td[@class='col price']")
    private WebElement productPrice;

    @FindBy(xpath = "//td[@class='col qty']")
    private WebElement productQty;

    @FindBy(xpath = "//td[@class='col subtotal']")
    private WebElement productSubtotal;

    @FindBy(xpath = "//tr[@class='mp_earn']")
    private WebElement earnedPointsInfo;

    @FindBy(xpath = "//tr[@class='subtotal']")
    private WebElement orderSubtotalInfo;

    @FindBy(xpath = "//tr[@class='shipping']")
    private WebElement shippingInfo;

    @FindBy(xpath = "//tr[@class='grand_total']")
    private WebElement grantTotalInfo;

    // Order Information

    @FindBy(xpath = "//div[@class='block block-order-details-view']")
    private WebElement orderInformationBlock;

    @FindBy(xpath = "//div[@class='box box-order-shipping-address']")
    private WebElement shippingAddressInfo;

    @FindBy(xpath = "//div[@class='box box-order-shipping-method']")
    private WebElement shippingMethodInfo;

    @FindBy(xpath = "//div[@class='box box-order-billing-address']")
    private WebElement billingAddressInfo;

    @FindBy(xpath = "//div[@class='box box-order-billing-method']")
    private WebElement paymentMethodInfo;

    public ViewOrder(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Back to My Orders>> button")
    public ViewOrder clickBackToMyOrdersButton() {
        click(backToMyOrdersButton);
        return this;
    }

    @Step("Click on <<Reorder>> button")
    public ViewOrder clickReorderButton() {
        click(reorderButton);
        return this;
    }

    @Step("Click on <<Invoices>> tab")
    public ViewOrder clickInvoicesTab() {
        click(invoicesTab);
        return this;
    }

   @Step("Verify Ordered items information: order ID {idOfOrder}, date {date}, status {statusOfOrder}, grand Total {grandTotal}")
    public ViewOrder verifyOrderedItemInformation(String idOfOrder, String date, String statusOfOrder, String grandTotal) {
        wait.until(ExpectedConditions.visibilityOf(orderId));
        scrollIntoView(orderedItemsTitle, driver);
        Assert.assertEquals(orderId.getText(), "Order # " + idOfOrder);
        Assert.assertEquals(orderDate.getText(), "Order Date: " + date);
        Assert.assertEquals(orderStatus.getText(), statusOfOrder);
        Assert.assertEquals(grantTotalInfo.getText(), "Grand Total " + grandTotal);
        return this;
    }

    @Step("Verify Invoice information: grand Total {grandTotal}")
    public ViewOrder verifyInvoiceInformation(String grandTotal) {
        wait.until(ExpectedConditions.visibilityOf(orderedItemsTitle));
        scrollIntoView(orderedItemsTitle, driver);
        Assert.assertTrue(orderedItemsTitle.getText().contains("Invoice #"), "Invoice number should be displayed");
        Assert.assertEquals(grantTotalInfo.getText(), "Grand Total " + grandTotal);
        return this;
    }

    @Step("Verify view order page")
    public ViewOrder verifyViewOrderPage() {
        wait.until(ExpectedConditions.visibilityOf(orderInformationBlock));
        Assert.assertTrue(orderId.getText().contains("Order #"), "Order number should be displayed");
        return this;
    }

    @Step("Verify order information")
    public ViewOrder verifyOrderInformation() {
        wait.until(ExpectedConditions.visibilityOf(orderInformationBlock));
        Assert.assertTrue(isElementPresent("//div[@class='box box-order-shipping-address']", driver), "Shipping address should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='box box-order-shipping-method']", driver), "Shipping method should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='box box-order-billing-address']", driver), "Billing address should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='box box-order-billing-method']", driver), "Billing method should be displayed");
        return this;
    }

    @Step("Verify earned points {points}")
    public ViewOrder verifyEarnedPoints(String points) {
        Assert.assertTrue(earnedPointsInfo.getText().contains(points), "Amount of points according to the rule should be displayed in order");
        return this;
    }

}
