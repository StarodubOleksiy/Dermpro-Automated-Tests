package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WishListSharing extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement pageTitle;

    @FindBy(xpath = "//textarea[@id='email_address']")
    private WebElement emailAddressesSharingInformation;

    @FindBy(xpath = "//button[@title='Share Wish List']")
    private WebElement shareWishListButton;

    public WishListSharing(WebDriver driver) {
        super(driver);
    }

    @Step("Enter email addresses sharing information {message}")
    public WishListSharing enterEmailAddressesSharingInformationMessage(String message) {
        sendText(emailAddressesSharingInformation, message);
        return this;
    }

    @Step("Verify if you on Wish List Sharing page")
    public WishListSharing verifyWishListSharingPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Wish List Sharing");
        return this;
    }

    @Step("Click on <<Share Wish List>> button")
    public WishListSharing clickShareWishListButton() {
        click(shareWishListButton);
        return this;
    }

}
