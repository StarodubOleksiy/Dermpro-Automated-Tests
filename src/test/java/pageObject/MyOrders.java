package pageObject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.bean.OrderData;

public class MyOrders extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']//span")
    private WebElement myOrdersTitle;

    @FindBy(xpath = "//a[@class='action view']/span[text()='View Order']")
    private List<WebElement> viewOrderButtonList;

    @FindBy(xpath = "//a[@class='action order']/span[text()='Reorder']")
    private List<WebElement> reorderButtonList;

    @FindBy(xpath = "//td[@class='col total']")
    private List<WebElement> orderTotal;

    @FindBy(xpath = "//td[@class='col id']")
    private List<WebElement> orderIds;

    @FindBy(xpath = "//td[@class='col date']")
    private List<WebElement> orderDate;

    @FindBy(xpath = "//td[@class='col status']")
    private List<WebElement> orderStatus;

    @FindBy(xpath = "//span[contains(text(),'Back')]")
    private WebElement backLink;

    public MyOrders(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on My Orders page")
    public MyOrders verifyMyOrderPage() {
        verifyTitle(myOrdersTitle, "My Orders");
        return this;
    }

    @Step("Click first <<Order view>> button")
    public MyOrders clickFirstOrderViewButton() {
        click(viewOrderButtonList.get(0));
        return this;
    }

    @Step("Click first <<Reorder>> button")
    public MyOrders clickFirstReorderButton() {
        click(reorderButtonList.get(0));
        return this;
    }

    @Step("Get first order data")
    public OrderData getFirstOrderData() throws ParseException {
        scrollIntoView(reorderButtonList.get(0), driver);
        String status = orderStatus.get(0).getText();
        String orderId = orderIds.get(0).getText();
        String grandTotal = orderTotal.get(0).getText();
        String dateText = orderDate.get(0).getText();
        Date myOrderDate = new SimpleDateFormat("M/d/yy").parse(dateText);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy");
        String date = simpleDateFormat.format(myOrderDate);
        OrderData od = new OrderData();
        od.setOrderId(orderId);
        od.setStatus(status);
        od.setGrandTotal(grandTotal);
        od.setDate(date);
        return od;
    }

    @Step("Verify order status by id")
    public MyOrders verifyOrderStatusById(String orderNumber, String status) {
        WebElement order = driver.findElement(By.xpath(String.format("//tr[td[@class='col id' and text()='%s']]/td[@class='col status']", orderNumber)));
        Assert.assertEquals(order.getText(), status);
        return this;
    }

    @Step("Click on <<Back>> link")
    public MyOrders clickBackLink() {
        click(backLink);
        return this;
    }

    @Step("Verification if orders are present on <<My Orders>> page")
    public boolean isOrdersPresent() {
        return isElementPresent("//th[contains(text(),'Order #')]", driver) &&
                orderIds.size() > 0;

    }
}
