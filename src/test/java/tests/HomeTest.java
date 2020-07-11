package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.MenuPageWithProducts;

import java.util.List;


public class HomeTest extends BaseTest {


    @Override
    public void initMethod() {
        UI.Header()
                .clickLogo();
        UI.Home().verifyBannerPresenceOnHomePage();
    }

    @TmsLink("TC_Home_Test_001")
    @Feature("DermPRO Home page")
    @Test(description = "Verify banner")
    public void test01BannerPresence() {
        UI.Home()
                .verifyBannerPresenceOnHomePage();
    }


    @TmsLink("TC_Home_Test_002")
    @Feature("DermPRO Home page")
    @Test(groups = "SO-16C", description = "Verify product on the first Tab")
    public void test02FirstTabProduct() {
        String firstTab = testData.getStore().getHomepageTabs().get(0);
        UI.Home()
                .clickOnTab(firstTab)
                .verifyProductsOnTab(1, testData.getStore().getFirstTabSKUs(),firstTab);
    }

    @TmsLink("TC_Home_Test_003")
    @Feature("DermPRO Home page")
    @Test(groups = "SO-17C", description = "Verify product on the second Tab")
    public void test03SecondTabProduct() {
        String secondTab = testData.getStore().getHomepageTabs().get(1);
        UI.Home()
                .clickOnTab(secondTab)
                .verifyProductsOnTab(2, testData.getStore().getSecondTabSKUs(),secondTab);
    }

    @TmsLink("TC_Home_Test_004")
    @Feature("DermPRO Home page")
    @Test(groups = "SO-18C", description = "Verify product on the third Tab")
    public void test04ThirdTabProduct() {
        String thirdTab = testData.getStore().getHomepageTabs().get(2);
        UI.Home()
                .clickOnTab(thirdTab.trim())
                .verifyProductsOnTab(3, testData.getStore().getThirdTabSKUs(),thirdTab);
    }

    @TmsLink("TC_Home_Test_005")
    @Feature("DermPRO Home page")
    @Test(description = "Verify shop name in Logo") //TODO test failed
    public void test05VerifyShopName() {
        String shopName = testData.getStore().getName();
        UI.Header().verifyLogoName(shopName);
    }

}
