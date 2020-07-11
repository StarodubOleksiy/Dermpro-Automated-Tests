package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AutoReplenishTest extends BaseTest {

    @Override
    public void initMethod() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
    }

    @TmsLink("TC_Auto_Replenish_Products_Test_001")
    @Feature("Auto_Replenish_Products")
    @Test(description = "Verify Auto Replenish products on home page")
    public void test01VerifyAutoReplenishProducts() {
        List<String> validAutoReplenishProductsList = new ArrayList<>();
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded()
                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1); //All
        UI.MenuPageWithProducts()
                .waitUntilCheckoutLoaderDisappears();
         testData.getStore().getAutoReplenishProductsNames().forEach(
                product -> {
                    if (UI.MenuPageWithProducts().getProductNameList().contains(product.toUpperCase())) {
                        validAutoReplenishProductsList.add(product);
                        UI.MenuPageWithProducts()
                                .clickProductByName(product);
                        UI.ProductDetails().verifyAutoReplenishMessage(testData.getStore().getDiscount());
                        testData.getStore().getPeriodicityOptionsMonth().forEach(
                                month -> {
                                    UI.ProductDetails().chooseSubscriptionOption(++month)
                                            .verifyAutoReplenishPriceIsDisplayed()
                                            .chooseSubscriptionOption(0)
                                            .verifyAutoReplenishPriceIsNotDisplayed();
                                }
                        );
                        UI.Menu()
                                .selectMenu(testData.getMenuVariable());
                        UI.MenuPageWithProducts().verifyMenuPageIsLoaded()
                                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1); //All
                        UI.MenuPageWithProducts()
                                .waitUntilCheckoutLoaderDisappears();
                    }
                }
        );
        UI.ProductDetails().verifyAutoReplenishProductListSize(validAutoReplenishProductsList.size(), testData.getStore().getAutoReplenishProductsNames().size());
    }

}
