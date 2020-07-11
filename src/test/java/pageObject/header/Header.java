package pageObject.header;

import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObject.PageObjectInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends PageObjectInitializer {

    @FindBy(xpath = "//div[contains(@class, 'header-primary')]//div[@class='welcome']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//div[@id='header-container']//a[@title='Account']")
    private WebElement account;

    @FindBy(xpath = "//div[@id='header-container']//a[@title='Sign In']")
    private WebElement logIn;

    @FindBy(xpath = "//div[@id='header-container']//a[@title='Wish List']")
    private WebElement wishList;

    @FindBy(xpath = "//div[@id='header-container']//a[@title='Sign Out']")
    private WebElement logOut;

    @FindBy(xpath = "//div[@id='header-container']//a[@title='Sign Up']")
    private WebElement signUp;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@title='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='item']//div[contains(@class,'logo-wrapper')]//*[@class='logo']")
    private WebElement logo;


    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Click on search input")
    public Header clickOnSearchInput() {
        click(searchInput);
        return this;
    }

    @Step("Enter search word {0}")
    public Header enterSearchWord(String word) {
        sendText(searchInput, word);
        return this;
    }

    @Step("Clear search input")
    public Header clearSearch() {
        clear(searchInput);
        return this;
    }


    @Step("Click on search button")
    public Header searchButtonClick() {
        click(searchButton);
        return this;
    }

    @Step("Click on <<Account>> link")
    public Header accountClick() {
        click(account);
        return this;
    }

    @Step("Click on <<Wish List>> link")
    public Header wishListClick() {
        click(wishList);
        return this;
    }

    @Step("Click on <<Sign In>> link")
    public Header logInClick() {
        click(logIn);
        return this;
    }

    @Step("Check <<Sign Out>> present")
    public boolean isLogOutPresent() {
        return isElementPresent("//div[@id='header-container']//a[@title='Sign Out']", driver);
    }

    @Step("Click on <<Sign Out>> link")
    public Header logOutClick() {
        click(logOut);
        return this;
    }

    @Step("Click on <<Sign Up>> link")
    public Header signUpClick() {
        click(signUp);
        return this;
    }

    @Step("Click on <<Logo>>")
    public Header clickLogo() {
        click(logo);
        return this;
    }

    @Step("Verify Logo name {shopName}")
    public Header verifyLogoName(String shopName) {
        wait.until(ExpectedConditions.visibilityOf(logo));
        Assert.assertEquals(logo.getText(), shopName);
        return this;
    }

    @Step("Verify successful Log In")
    public Header verifyDashboardForSignedUser() {
        verifyMessage("Welcome, ");
        return this;
    }

    @Step("Verify successful Log In")
    public Header verifyDashboardForGuest() {
        verifyMessage("Welcome to our Online Store");
        return this;
    }

    private void verifyMessage(String verifyMessage) {
        wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        scrollIntoView(welcomeMessage, driver);
        Assert.assertTrue(welcomeMessage.getText().toLowerCase().contains(verifyMessage.toLowerCase()), String.format("Welcome message should contain '%s'", verifyMessage));
    }
}
