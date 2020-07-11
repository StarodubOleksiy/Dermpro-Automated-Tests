package tests;

import configuration.BaseMethod;
import configuration.BrowserFactory;
import configuration.PropertyLoader;
import creation.bean.MenuCategory;
import data.StoreConvertor;
import data.TestData;
import data.bean.Store;
import org.testng.annotations.*;
import pageObject.PageObjectInitializer;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class BaseTest extends BaseMethod {

    private static String browserBaseUrl;
    private static Store store;
    protected static TestData testData;

    protected PageObjectInitializer UI;
    protected WebDriver driver;

    private int retryConfigMethodCount = PropertyLoader.retryConfigMethodCount;

    private int configCounter = 0;
    private int methodCounter = 0;


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
        driver = BrowserFactory.createDriver(browserBaseUrl);
        UI = new PageObjectInitializer(driver);
        setDriverAndWait(driver);

        //dynamically add product names from store site
        if (store.getProductsNames() == null || store.getProductsNames().isEmpty()) {
            List<MenuCategory> categories = store.getMainMenuCategories();
            if (categories == null || categories.isEmpty()) {
                System.out.println("No categories found");
                return;
            }
            UI.Menu().selectMenu(categories.get(0).getName());
            waitUntilProductsPageIsLoaded();
            List<String> names = UI.MenuPageWithProducts().getProductNamesList();
//            List<String> xxx = names.stream().filter(n ->
//                    (getPrice(n) % 2 != 0)
//            ).collect(Collectors.toList());
            store.setProductNames(names);
            testData.setStore(store);
        }
        handledBeforeClass();
    }

    private int getPrice(String n) {
        String stringPrice = UI.MenuPageWithProducts().getPriceByProductName(n);
        return Integer.parseInt(stringPrice.substring(1, stringPrice.indexOf(".")));
    }

    private void handledBeforeClass() {
        if (retryConfigMethodCount == 0) {
            init();
        } else {
            try {
                configCounter++;
                init();
            } catch (Exception e) {
                System.out.println(String.format("Exception in beforeClass: %s", e));

                if (configCounter < retryConfigMethodCount + 1) {
                    System.out.println(String.format("Retry #%s of beforeClass", configCounter));
                    configCounter++;
                    init();
                }
            }
        }
    }

    protected void init() {
    }

    @BeforeMethod
    public void handledBeforeMethod() {
        if (retryConfigMethodCount == 0) {
            init();
        } else {
            try {
                methodCounter++;
                initMethod();
            } catch (Exception e) {
                System.out.println(String.format("Exception in beforeMethod: %s", e));
                if (methodCounter < retryConfigMethodCount + 1) {
                    System.out.println(String.format("Retry #%s of beforeMethod", methodCounter));
                    methodCounter++;
                    initMethod();
                }
            }
        }
    }

    protected void initMethod() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        try {
            if (UI.Header().isLogOutPresent()) {
                UI.Header().logOutClick();
            }
        } catch (Exception e) {
            System.out.println("Exception happened during logout:" + e);
        }
        driver.quit();
    }
}
