package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObjectInitializer;

public class AdminSignIn extends PageObjectInitializerAdmin {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button//span[text()='Sign in']")
    private WebElement signInButtonInput;

    public AdminSignIn(WebDriver driver) {
        super(driver);
    }


    @Step("Click  <<Sign In>> button")
    public AdminSignIn clickSignIn() {
        clickAdmin(signInButtonInput);
        return this;
    }

    @Step("Enter username {userName}")
    public AdminSignIn enterUserName(String userName) {
        clearAdmin(userNameInput);
        sendTextAdmin(userNameInput, userName);
        return this;
    }

    @Step("Enter password {password}")
    public AdminSignIn enterPassword(String password) {
        clearAdmin(passwordInput);
        sendTextAdmin(passwordInput, password);
        return this;
    }
}
