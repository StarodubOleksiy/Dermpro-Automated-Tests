package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class ServicesTest extends BaseTest {

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-35")) {
            UI.clearCart();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-35")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getServices().getMenuTitle());
        }
    }


    @TmsLink("TC_Services_001")
    @Feature("Services")
    @Test(groups = "SO-35", description = "Find treatments on category menu page")
    public void test01FindTreatments() {
        UI.ServicesList().verifyTreatmentsOnPage();
    }

    @TmsLink("TC_Services_002")
    @Feature("Services")
    @Test(groups = "SO-35", description = "Click on current treatment product")
    public void test02ClickInCurrentTreatmentProduct() {
        String productName = UI.ServicesList().getProductName(0);
        UI.ServicesList().clickProductByName(productName);
        UI.ProductDetails().
                waitUntilProductDetailsLoaded()
                .verifyProductDetailsPage(productName);
    }

    @TmsLink("TC_Services_003")
    @Feature("Services")
    @Test(groups = "SO-35", description = "Add services product to cart")
    public void test03AddTreatmentToCart() {
        String productName = UI.ServicesList().getProductName(0);
        UI.ServicesList()
                .addToCartClick(productName);
        UI.Cart()
                .waitUntilCartElementsLoaded()
                .verifyProductAddToCart(productName)
                .clickRemoveItemButton();
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
