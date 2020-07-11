package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.List;

public class SiteMapTest extends BaseTest {

    @Override
    public void init() {
        UI.FooterBottom().siteMapClick();

    }

    @TmsLink("TC_Site_Map_Test_001")
    @Feature("DermPRO Site Map page")
    @Test(description = "Verify Site Map Page")
    public void test01VerifySiteMapPage() {
        UI.SiteMap().verifySiteMapPage()
                .clickProduct(testData.getFirstProductName());
        UI.ProductDetails()
                .verifyProductDetailsPage(testData.getFirstProductName());
    }

    @TmsLink("TC_Site_Map_Test_002")
    @Feature("DermPRO Site Map page")
    @Test(description = "Verify Pages On Site Map")
    public void test02VerifyPagesOnSiteMap() {
        List<String> pages = UI.SiteMap().createPageList();
        for (int i = 0; i < pages.size();  i++) {
            UI.SiteMap().clickPage(pages.get(i));
            if (i < (pages.size() - 1))
                UI.SiteMap().verifyPage(pages.get(i));
            else
                UI.Home()
                        .verifyBannerPresenceOnHomePage();
            UI.FooterBottom().siteMapClick();
        }
    }
}
