package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PaginationTest extends BaseTest {


    @Override
    public void initMethod() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
    }

    @TmsLink("TC_Pagination_Test_001")
    @Feature("Pagination")
    @Test(description = "Verify pagination on home page")
    public void test01VerifyShowPerPageOptionsOnGridView() {
        UI.MenuPageWithProducts()
                .verifyShowPerPageOptionsOnGridView();
    }

    @TmsLink("TC_Pagination_Test_002")
    @Feature("Pagination")
    @Test(description = "Verify pagination show per page options on list view")
    public void test02VerifyShowPerPageOptionsOnListView() {
        UI.MenuPageWithProducts()
                .clickModeListButton();
        UI.MenuPageWithProducts()
                .verifyShowPerPageOptionsOnListView();
    }


    @TmsLink("TC_Pagination_Test_003")
    @Feature("Pagination")
    @Test(description = "Verify total number of all items on grid view")
    public void test03VerifyTotalNumberOfAllItemsOnGridView() {
        UI.MenuPageWithProducts()
                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1); //All
        UI.MenuPageWithProducts()
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyNumberOfAllItems();
    }

    @TmsLink("TC_Pagination_Test_004")
    @Feature("Pagination")
    @Test(description = "Verify total number of all items on list view")
    public void test04VerifyTotalNumberOfAllItemsOnListView() {
        UI.MenuPageWithProducts()
                .clickModeListButton()
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1) //All
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyNumberOfAllItems();
    }

    @TmsLink("TC_Pagination_Test_005")
    @Feature("Pagination")
    @Test(description = "Verify number view on Grid page")
    public void test05VerifyNumberViewOnGridPage() {
        UI.MenuPageWithProducts()
                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1) //40
                .waitUntilCheckoutLoaderDisappears();
        if (UI.MenuPageWithProducts().getPagesSize() > 1) {
            UI.MenuPageWithProducts().verifyNumberOfPages(UI.MenuPageWithProducts().getPagesSize() - 1, UI.MenuPageWithProducts().calculationPages())
                    .chooseShowPerPageOption(0)
                    .waitUntilCheckoutLoaderDisappears();
            String numberOfProductsOnPage = UI.MenuPageWithProducts().getFirstValueOfToolbarAmount();
            if (UI.MenuPageWithProducts().verifyIfNextButtonIsPresent()) {
                UI.MenuPageWithProducts()
                        .clickOnNextPageButton()
                        .waitUntilCheckoutLoaderDisappears();
                UI.MenuPageWithProducts()
                        .verifyFirstValueOfToolbarAmount(String.valueOf(Integer.parseInt(numberOfProductsOnPage) + Integer.parseInt(UI.MenuPageWithProducts().getValueOfShowPerPageOptionByIndex(0).replaceAll(" ", ""))));
            }
            if (UI.MenuPageWithProducts().verifyIfPreviousButtonIsPresent()) {
                UI.MenuPageWithProducts()
                        .clickOnPreviousPageButton()
                        .waitUntilCheckoutLoaderDisappears();
                UI.MenuPageWithProducts()
                        .verifyFirstValueOfToolbarAmount(numberOfProductsOnPage);
            }
        }
    }


    @TmsLink("TC_Pagination_Test_006")
    @Feature("Pagination")
    @Test(description = "Verify number of pages on List page")
    public void test06VerifyNumberOfPagesOnListPage() {
        UI.MenuPageWithProducts()
                .clickModeListButton()
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .chooseShowPerPageOption(UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1)// 30
                .waitUntilCheckoutLoaderDisappears();
        if (UI.MenuPageWithProducts().getPagesSize() > 1) {
            UI.MenuPageWithProducts().verifyNumberOfPages(UI.MenuPageWithProducts().getPagesSize() - 1, UI.MenuPageWithProducts().calculationPages())
                    .chooseShowPerPageOption(0)
                    .waitUntilCheckoutLoaderDisappears();
            String numberOfProductsOnPage = UI.MenuPageWithProducts().getFirstValueOfToolbarAmount();
            if (UI.MenuPageWithProducts().verifyIfNextButtonIsPresent()) {
                UI.MenuPageWithProducts()
                        .clickOnNextPageButton()
                        .waitUntilCheckoutLoaderDisappears();
                UI.MenuPageWithProducts()
                        .verifyFirstValueOfToolbarAmount(String.valueOf(Integer.parseInt(numberOfProductsOnPage) + Integer.parseInt(UI.MenuPageWithProducts().getValueOfShowPerPageOptionByIndex(0).replaceAll(" ", ""))));
            }
            if (UI.MenuPageWithProducts().verifyIfPreviousButtonIsPresent()) {
                UI.MenuPageWithProducts()
                        .clickOnPreviousPageButton()
                        .waitUntilCheckoutLoaderDisappears();
                UI.MenuPageWithProducts()
                        .verifyFirstValueOfToolbarAmount(numberOfProductsOnPage);
            }
        }
    }

    @TmsLink("TC_Pagination_Test_007")
    @Feature("Pagination")
    @Test(description = "Verify all per page options on list page")
    public void test07VerifyShowPerPageOptionsOnListPage() {
        UI.MenuPageWithProducts()
                .clickModeListButton()
                .waitUntilCheckoutLoaderDisappears();
        for (int i = 0; i < UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1; i++) {
            UI.MenuPageWithProducts()
                    .chooseShowPerPageOption(i)
                    .waitUntilCheckoutLoaderDisappears();
            UI.MenuPageWithProducts()
                    .verifyProductsPerPage(i);
        }

    }

    @TmsLink("TC_Pagination_Test_008")
    @Feature("Pagination")
    @Test(description = "Verify all per page options on grid page")
    public void test08VerifyShowPerPageOptionsOnGridPage() {
        for (int i = 0; i < UI.MenuPageWithProducts().getShowPerPageOptionsSize() - 1; i++) {
            UI.MenuPageWithProducts()
                    .chooseShowPerPageOption(i)
                    .waitUntilCheckoutLoaderDisappears();
            UI.MenuPageWithProducts()
                    .verifyProductsPerPage(i);
        }

    }

}
