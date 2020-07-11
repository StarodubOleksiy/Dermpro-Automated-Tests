package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ServicesList extends ProductListContainer {

    @FindBy(xpath = "//div[@class='category-description']//h1")
    private WebElement treatmentListTitle;


    public ServicesList(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on Services list page")
    public ServicesList verifyTreatmentsOnPage() {
        Assert.assertTrue(isElementPresent("//div[@class='category-description']",driver));
        Assert.assertNotEquals(getProductsListSize(),0);
        return this;
    }

}
