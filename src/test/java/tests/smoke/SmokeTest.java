package tests.smoke;

import configuration.BaseMethod;
import configuration.BrowserFactory;
import data.StoreConvertor;
import data.TestData;
import data.bean.Store;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.PageObjectInitializer;
import java.util.List;

public class SmokeTest extends BaseMethod {

    private static String browserBaseUrl;
    private static Store store;
    protected static TestData testData;
    protected PageObjectInitializer UI;
    protected WebDriver driver;

    @Parameters({"browserUrl", "fileNameSuffix", "useGuestOnly"})
    @BeforeSuite
    public void beforeSuite(@Optional String browserUrl, @Optional String fileNameSuffix, @Optional String useGuestOnly) throws Exception {
        browserBaseUrl = browserUrl;
        try {
            store = StoreConvertor.readStoreDataFromFile(fileNameSuffix);
            testData = new TestData();
        } catch (Exception e) {
            System.out.println("No " + fileNameSuffix + " json file present!");
        }
    }

    @BeforeTest(alwaysRun = true)
    public void suiteSetup() {
        BrowserFactory.detectBrowser();
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            driver = BrowserFactory.createDriver(browserBaseUrl);
            UI = new PageObjectInitializer(driver);
            setDriverAndWait(driver);
            List<String> names = UI.MenuPageWithProducts().getProductNamesList();
            store.setProductNames(names);
            testData.setStore(store);
        } catch (Exception e) {
            if (driver != null) {
                driver.quit();
            }
            throw e;
        }
    }

    @TmsLink("TC_Smoke_001")
    @Feature("DermPRO Smoke test")
    @Test(description = "Verify successful Sign in")
    public void test01SuccessfulLogIn() {
        UI.Header()
                .logInClick();
        UI.SignIn()
                .quickLogin(testData.getStore());
        UI.Header()
                .verifyDashboardForSignedUser();
    }

    @TmsLink("TC_Smoke_002")
    @Feature("DermPRO Smoke test")
    @Test(description = "Verify products page in first tab menu")
    public void test02VerifyPageWithProducts() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .verifyProductsPresence();
    }

    @AfterClass()
    public void afterClass() {
        driver.quit();
    }

}