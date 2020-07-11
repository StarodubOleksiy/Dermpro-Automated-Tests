package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;

public class AdminDashboard extends PageObjectInitializerAdmin {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement adminDashboardHeader;

    @FindBy(xpath = "//button[@class='action-close']")
    private List<WebElement> popupCloseButton;

    @FindBy(xpath = "//li[@id='menu-magento-backend-marketing']")
    private WebElement marketingMenu;

    @FindBy(xpath = "//li[contains(@class,'item-catalog-reviews-ratings-reviews-all')]/a")
    private WebElement reviewSubmenu;

    public AdminDashboard(WebDriver driver) {
        super(driver);
    }


    @Step("Verify admin dashboard")
    public AdminDashboard verifyAccountDashboardInformation() {
        Assert.assertEquals(adminDashboardHeader.getText(), "DASHBOARD");
        return this;
    }

    @Step("Check if popup message is displayed")
    private boolean isPopupDisplayed() {
        return popupCloseButton.get(0).isDisplayed();
    }

    @Step("Close popup messages")
    public AdminDashboard closePopupMessages() {
        popupCloseButton.forEach(n -> {
            sleep(1000);
            if (isPopupDisplayed()) {
                n.click();
            }
        });
        return this;
    }

    @Step("Click on Menu by name {menuName}")
    public AdminDashboard clickMenu(String menuName) {
        WebElement menu = driver.findElement(By.xpath(String.format("//li[contains(@class,'parent  level-0')]/a/span[contains(text(),'%s')]", menuName)));
        clickAdmin(menu);
        return this;
    }

    @Step("Click on Submenu by name {subMenuName}")
    public AdminDashboard clickSubMenu(String subMenuName) {
        WebElement subMenu = driver.findElement(By.xpath(String.format("//div[@class='submenu']//a/span[text()='%s']", subMenuName)));
        scrollIntoView(subMenu, driver);
        jsClick(subMenu, driver);
        return this;
    }

}
