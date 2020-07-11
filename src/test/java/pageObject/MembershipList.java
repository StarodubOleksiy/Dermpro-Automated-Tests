package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MembershipList extends ProductListContainer {

    public MembershipList(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on Membership list page")
    public MembershipList verifyMembershipListPage(String pageName) {
        Assert.assertTrue(isElementPresent(String.format("//span[contains(text(),'%s')]", pageName), driver), String.format("Membership page '%s' should be present", pageName));
        return this;
    }

    @Step("Verify if Membership name is present on the page {membershipName}")
    public MembershipList verifyIfMembershipNameIsPresent(String membershipName) {
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", membershipName), driver), String.format("Membership name '%s' should be present", membershipName));
        return this;
    }
}
