package creation;

import creation.bean.StoreBean;
import creation.bean.StoreDetailedBean;
import creation.bean.StoreConfigBean;
import creation.features.OptionsStoreConvertor;
import data.bean.Store;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {

    private static OptionsStoreConvertor convertor;

    private static boolean allStores = false;
    private static boolean guestOnly = false;
    private static boolean useTest = false;

    private static String storeName;

    private static List<String> fileNames = new ArrayList<>();


    public static void main(String... args) {
        parseArguments(args);
        convertor = new OptionsStoreConvertor();

        if (allStores) {
            processDataForStoresList();
        } else {
            StoreDetailedBean storeDetails = DermPRORestClient.getStoreByName(storeName);
            processStoreOptions(storeDetails);
        }

        if (guestOnly) {
            writeAllTestNg();
        }

        System.out.println("All went ok");
    }

    private static void parseArguments(String[] args) {
        if (args.length != 0) {
            try {
                if (args[0].contains("All Stores")) {
                    allStores = true;
                } else
                    storeName = args[0].substring("stores=".length());

                String guestOnlyValue = args[1].substring("guestOnly=".length());
                guestOnly = BooleanUtils.toBooleanObject(guestOnlyValue);

                String useTestValue = args[2].substring("useTest=".length());
                useTest = BooleanUtils.toBooleanObject(useTestValue);

            } catch (Exception e) {
                printExplanationAndExit();
            }
        } else
            printExplanationAndExit();
    }

    private static void printExplanationAndExit() {
        System.out.println("Please add Program Arguments in Edit Configurations section like '\"stores=All Stores\" \"guestOnly=false\" \"useTest=true\"'. For 'stores' value can be specific store name or 'All Stores', for 'guestOnly' values are 'true/false' and for 'useTest' values are 'true/false'");
        System.exit(0);
    }


    private static void writeAllTestNg() {
        try {
            FileWriter fileWriter = new FileWriter("testng_all.xml");

            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            printWriter.print("<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n");
            printWriter.print("<suite name=\"All Tests\" parallel=\"classes\" thread-count=\"1\" data-provider-thread-count=\"1\" verbose=\"10\">\n");
            printWriter.print("<listeners>\n");
            printWriter.print("   <listener class-name=\"listeners.RetryTestListener\"></listener>\n");
            printWriter.print("   <listener class-name=\"listeners.AllureListener\"></listener>\n");
            printWriter.print("</listeners>\n");
            printWriter.print("<suite-files>\n");

            fileNames.forEach(fileName -> {
                printWriter.print("<suite-file path=\"" + fileName + "\" />\n");

            });
            printWriter.print("</suite-files>\n");
            printWriter.print("</suite>");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeStoreTestNg(String fileName, Store store) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            printWriter.print("<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n");
            printWriter.print("<suite name=\"All Tests\" parallel=\"classes\" thread-count=\"1\" data-provider-thread-count=\"1\" verbose=\"10\">\n");

            printWriter.print("<listeners>\n");
            printWriter.print("   <listener class-name=\"listeners.RetryTestListener\"></listener>\n");
            printWriter.print("   <listener class-name=\"listeners.AllureListener\"></listener>\n");
            printWriter.print("</listeners>\n");

            printWriter.print("<parameter name=\"browserUrl\" value=\"" + store.getUrl() + "\"/>\n");
            printWriter.print("<parameter name=\"fileNameSuffix\" value=\"" + StringEscapeUtils.escapeXml11(store.getCode()) + "\"/>\n");
            printWriter.print("<test name=\"DermPro tests\">\n");
            //Groups
            printWriter.print("<groups>\n");
            printWriter.print("<run>\n");

            store.getFeatureStates().forEach(fs -> {
                if (!fs.isEnabled()) {
                    printWriter.print("<exclude name=\"" + fs.getCode() + "\"/>\n");
                }
            });

            printWriter.print("</run>\n");
            printWriter.print("</groups>\n");

            printWriter.print("<classes>\n");
            printWriter.print("<class name=\"tests.smoke.SmokeTest\" />\n");
            printWriter.print("<class name=\"tests.SignInTest\" />\n");
            printWriter.print("<class name=\"tests.SignUpTest\" />\n");
            printWriter.print("<class name=\"tests.AccountDashboardTest\" />\n");
            printWriter.print("<class name=\"tests.AccountInformationTest\" />\n");
            printWriter.print("<class name=\"tests.AddressBookTest\" />\n");
            printWriter.print("<class name=\"tests.AdvancedSearchTest\" />\n");
            printWriter.print("<class name=\"tests.CartPopUpTest\" />\n");
            printWriter.print("<class name=\"tests.GuestCheckoutTest\" />\n");
            printWriter.print("<class name=\"tests.CheckoutTest\" />\n");
            printWriter.print("<class name=\"tests.CompareProductsTest\" />\n");
            printWriter.print("<class name=\"tests.ContactUsTest\" />\n");
            printWriter.print("<class name=\"tests.FooterTest\" />\n");
            printWriter.print("<class name=\"tests.GiftCardTest\" />\n");
            printWriter.print("<class name=\"tests.signeduser.SignedUserGiftCardTest\" />\n");
            printWriter.print("<class name=\"tests.HomeTest\" />\n");
            printWriter.print("<class name=\"tests.MembershipTest\" />\n");
            printWriter.print("<class name=\"tests.signeduser.SignedUserMembershipTest\" />\n");
            printWriter.print("<class name=\"tests.MenuPageWithProductsTest\" />\n");
            printWriter.print("<class name=\"tests.MenuTest\" />\n");
            printWriter.print("<class name=\"tests.MyGiftCardsTest\" />\n");
            printWriter.print("<class name=\"tests.MyOrdersTest\" />\n");
            printWriter.print("<class name=\"tests.MyRewardGuestTest\" />\n");
            printWriter.print("<class name=\"tests.MyRewardTest\" />\n");
            printWriter.print("<class name=\"tests.MyStoredPaymentAccountsTest\" />\n");
            printWriter.print("<class name=\"tests.NewsletterSubscriptionsTest\" />\n");
            printWriter.print("<class name=\"tests.ProductDetailsFrequentlyTest\" />\n");
            printWriter.print("<class name=\"tests.ProductDetailsTest\" />\n");
            printWriter.print("<class name=\"tests.signeduser.SignedUserProductDetailsTest\" />\n");
            printWriter.print("<class name=\"tests.ReviewTest\" />\n");
            printWriter.print("<class name=\"tests.SearchTermsTest\" />\n");
            printWriter.print("<class name=\"tests.SearchTest\" />\n");
            printWriter.print("<class name=\"tests.ServicesTest\" />\n");
            printWriter.print("<class name=\"tests.signeduser.SignedUserServicesTest\" />\n");
            printWriter.print("<class name=\"tests.ShoppingCartTest\"/>\n");
            printWriter.print("<class name=\"tests.ShoppingOptionsTest\" />\n");
            printWriter.print("<class name=\"tests.SiteMapTest\" />\n");
            printWriter.print("<class name=\"tests.SubscribeTest\" />\n");
            printWriter.print("<class name=\"tests.WishListTest\" />\n");
            printWriter.print("<class name=\"tests.admin.AdminSignInTest\" />\n");
            printWriter.print("<class name=\"tests.integration.IntegrationCheckoutTest\" />\n");
            printWriter.print("<class name=\"tests.integration.IntegrationReviewTest\" />\n");
            printWriter.print("</classes>\n");

            printWriter.print("</test>\n");
            printWriter.print("</suite>");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDataForStoresList() {
        List<StoreBean> stores = DermPRORestClient.getStoreList().getStores();
        int i = 0;
        stores.stream().forEach(s -> {
            if (s.getId() == 11 || s.getId() == 61) { //TODO remove once all stores will be ready
                StoreDetailedBean storeDetails = DermPRORestClient.getStoreById(s.getId());
                processStoreOptions(storeDetails);

            }
        });
    }

    private static void processStoreOptions(StoreDetailedBean storeDetails) {
        List<StoreConfigBean> configs = storeDetails.getStoreConfigs();

        Store store = convertor.getStore(configs, useTest, guestOnly);

        JsonManager.writeJson(store);

        String fileName = "testng_" + store.getCode() + ".xml";
        fileNames.add(fileName);

        writeStoreTestNg(fileName, store);
    }
}
