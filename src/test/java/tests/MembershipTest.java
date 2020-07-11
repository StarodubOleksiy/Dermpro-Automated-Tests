package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class MembershipTest extends BaseTest {

    private String memberShipName;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-37A")) {
            memberShipName = testData.getStore().getMembership().getProductName();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-37A")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getMembership().getMenuTitle());
        }
    }

    @TmsLink("TC_Membership_001")
    @Feature("Membership")
    @Test(groups = "SO-37A", description = "Verify Membership page")
    public void test01VerifyMembershipPage() {
        UI.MembershipList()
                .verifyMembershipListPage(testData.getStore().getMembership().getMenuTitle());
    }

    @TmsLink("TC_Membership_002")
    @Feature("Membership")
    @Test(groups = "SO-37A", description = "Verify Membership name")
    public void test02VerifyMembershipNamePrices() {
        UI.MembershipList()
                .verifyIfMembershipNameIsPresent(memberShipName);
    }

    @TmsLink("TC_Membership_003")
    @Feature("Membership")
    @Test(groups = "SO-37A", description = "Verify Membership name preview")
    public void test03VerifyMembershipPreview() {
        UI.MembershipList()
                .addToCartClick(memberShipName);
        UI.Membership()
                .verifySelectedMembershipPage(memberShipName);
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
