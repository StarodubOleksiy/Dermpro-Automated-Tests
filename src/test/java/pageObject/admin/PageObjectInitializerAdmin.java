package pageObject.admin;

import configuration.BaseMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.*;
import pageObject.footer.FooterBottom;
import pageObject.footer.FooterPrimary;
import pageObject.footer.FooterSecondary;
import pageObject.header.Header;
import pageObject.menu.Menu;

public class PageObjectInitializerAdmin extends BaseMethod {

    protected WebDriver driver;

    public PageObjectInitializerAdmin(WebDriver driver) {
        this.driver = driver;
    }

    public AdminSignIn AdminSignIn() {
        return PageFactory.initElements(driver, AdminSignIn.class);
    }

    public AdminDashboard AdminDashboard() {
        return PageFactory.initElements(driver, AdminDashboard.class);
    }

    public AdminReview AdminReview() {
        return PageFactory.initElements(driver, AdminReview.class);
    }

    public AdminReviewDetails AdminReviewDetails() {
        return PageFactory.initElements(driver, AdminReviewDetails.class);
    }

    public AdminOrders AdminOrders() {
        return PageFactory.initElements(driver, AdminOrders.class);
    }

    public AdminOrderDetails AdminOrderDetails() {
        return PageFactory.initElements(driver, AdminOrderDetails.class);
    }

    public  WebDriver getWebDriver()
    {
        return driver;
    }
}
