package tests.admin;

import configuration.BaseMethod;
import configuration.BrowserFactory;
import configuration.PropertyLoader;
import creation.bean.MenuCategory;
import data.StoreConvertor;
import data.TestData;
import data.bean.Store;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.PageObjectInitializer;
import pageObject.admin.PageObjectInitializerAdmin;

import java.util.List;


public class AdminBaseTest extends BaseMethod {

    private static String browserBaseUrl;
    private static Store store;
    protected static TestData testData;

    protected PageObjectInitializer UI;
    protected PageObjectInitializerAdmin UI_Admin;
    protected WebDriver userDriver;
    protected WebDriver adminDriver;


    private int retryConfigMethodCount = PropertyLoader.retryConfigMethodCount;
    private String adminPageUrl =PropertyLoader.AdminPageUrl;

    @Parameters({"browserUrl", "fileNameSuffix"})
    @BeforeSuite
    public void beforeSuite(@Optional String browserUrl, @Optional String fileNameSuffix) throws Exception {
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
        userDriver = BrowserFactory.createDriver(browserBaseUrl);
        adminDriver = BrowserFactory.createDriver(adminPageUrl);

        UI = new PageObjectInitializer(userDriver);
        UI_Admin = new PageObjectInitializerAdmin(adminDriver);

        setDriverAndWait(userDriver);
        setAdminDriverAndWait(adminDriver);

        //dynamically add product names from store site
        if (store.getProductsNames() == null || store.getProductsNames().isEmpty()) {
            List<MenuCategory> categories = store.getMainMenuCategories();
            if (categories == null || categories.isEmpty()) {
                System.out.println("No categories found");
                return;
            }
            UI.Menu().selectMenu(categories.get(0).getName());

            List<String> names = UI.MenuPageWithProducts().getProductNamesList();
            store.setProductNames(names);
            testData.setStore(store);
        }
        handledBeforeClass();

    }

    private void handledBeforeClass() {
        int counter = 0;
        try {
            counter++;
            init();
        } catch (Exception e) {
            if (counter < retryConfigMethodCount+1) {
                System.out.println(String.format("Retry #%s of beforeClass", counter));
                counter++;
                init();
            }
        }
    }

    protected void init() {
    }

    @BeforeMethod
    public void handledBeforeMethod() {
        int counter = 0;
        try {
            counter++;
            initMethod();
        } catch (Exception e) {
            if (counter < retryConfigMethodCount+1) {
                System.out.println(String.format("Retry #%s of beforeMethod", counter));
                counter++;
                initMethod();
            }
        }
    }

    protected void initMethod() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        userDriver.quit();
        adminDriver.quit();
    }
}
