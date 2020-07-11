package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedInCartPopUpTest extends BaseTest {

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }

            UI.clearCart();
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickAddToCart(testData.getFirstProductName());
            UI.Cart().waitUntilCartPageIsLoaded();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickAddToCart(testData.getFirstProductName());
            UI.Cart().waitUntilCartPageIsLoaded();
        }
    }

    @TmsLink("TC_Signed_Card_Pop_Up_001")
    @Feature("Card_Pop_Up")
    @Test(groups = "signedUser", description = "Verify Cart Pop Up")
    public void test01VerifyCartPopUp() {
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .verifyCartPopUpWithData();
    }



    @TmsLink("TC_Signed_Card_Pop_Up_002")
    @Feature("Card_Pop_Up")
    @Test(groups = "signedUser", description = "Select product by clicking on it's title")
    public void test02SelectProductByTitle() {
        UI.CartPopUp()
                .clickOnCurrentProductInCart(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilProductDetailsLoaded()
                .verifyProductDetailsPage(testData.getFirstProductName());
    }







    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
