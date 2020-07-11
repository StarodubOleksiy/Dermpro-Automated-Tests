package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddressBookTest extends BaseTest {


    @Override
    protected void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header().logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());
            UI.Header()
                    .verifyDashboardForSignedUser();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickAddressBook();
            if (UI.AddressBook().isAddressBookEmpty()) {
                UI.AddressBook()
                        .enterTelephone(testData.getPhoneNumber())
                        .enterStreetAddress1(testData.getHomeAddress())
                        .enterStreetAddress2(testData.getSecondaryAddress())
                        .enterCity(testData.getCity())
                        .setStateProvince(testData.getState())
                        .enterZipPostalCode(testData.getZipCode())
                        .clickSaveAddressButton()
                        .verifyAddressBookMessage("You saved the address.");
            }
        }
   }

    @TmsLink("TC_Address_Book_Test_001")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser",  description = "Address book Success Flow")
    public void test01SuccessFlow() {
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSaveAddressButton()
                .verifyAddressBookMessage("You saved the address.");
    }

    @TmsLink("TC_My_Address_Book_Test_002")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser",  description = "Address book test empty fields")
    public void test02EmptyFields() {
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .clickSaveAddressButton()
                .verifyEmptyFields();
    }

    @TmsLink("TC_My_Address_Book_Test_003")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser", description = "Address book test edit address contact")
    public void test03EditAddress() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String address = testData.getHomeAddress();
        String city = testData.getCity();
        String telephone = testData.getPhoneNumber();
        UI.AddressBook()
                .clickEditAddress();
        UI.AddressBook()
                .clearFirstName()
                .enterFirstName(firstName)
                .clearLastName()
                .enterLastName(lastName)
                .clearTelephone()
                .enterTelephone(telephone)
                .clearStreetAddress1()
                .enterStreetAddress1(address)
                .clearCity()
                .enterCity(testData.getCity())
                .clickSaveAddressButton()
                .verifyAddressBookMessage("You saved the address.")
                .verifyChangedFields(firstName, lastName, address, city, telephone);
    }

    @TmsLink("TC_Address_Book_Test_004")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser",  description = "Address book success save billing address contact")
    public void test04SaveBillingAddressContact() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String city = testData.getCity();
        String address = testData.getHomeAddress();
        String phoneNumber = testData.getPhoneNumber();
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .clearFirstName()
                .enterFirstName(firstName)
                .clearLastName()
                .enterLastName(lastName)
                .enterTelephone(phoneNumber)
                .enterStreetAddress1(address)
                .enterCity(city)
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickUseAsMyDefaultBillingAddressCheckbox()
                .clickSaveAddressButton()
                .verifyAddressBookMessage("You saved the address.")
                .verifySavedBillingAddressContact(firstName, lastName, address, city, phoneNumber);
    }

    @TmsLink("TC_Address_Book_Test_005")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser",  description = "Address book success save shipping address contact")
    public void test05SaveShippingAddressContact() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String city = testData.getCity();
        String address = testData.getHomeAddress();
        String phoneNumber = testData.getPhoneNumber();
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .clearFirstName()
                .enterFirstName(firstName)
                .clearLastName()
                .enterLastName(lastName)
                .enterTelephone(phoneNumber)
                .enterStreetAddress1(address)
                .enterCity(city)
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickUseAsMyDefaultShippingAddressCheckbox()
                .clickSaveAddressButton()
                .verifyAddressBookMessage("You saved the address.")
                .verifySavedShippingAddressContact(firstName, lastName, address, city, phoneNumber);
    }

    @TmsLink("TC_Address_Book_Test_006")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser", description = "Change billing to shipping address contact")
    public void test06ChangeBillingAddressToShipping() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String city = testData.getCity();
        String address = testData.getHomeAddress();
        String phoneNumber = testData.getPhoneNumber();
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .clearFirstName()
                .enterFirstName(firstName)
                .clearLastName()
                .enterLastName(lastName)
                .enterTelephone(phoneNumber)
                .enterStreetAddress1(address)
                .enterCity(city)
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickUseAsMyDefaultBillingAddressCheckbox()
                .clickSaveAddressButton()
                .verifySavedBillingAddressContact(firstName, lastName, address, city, phoneNumber)
                .clickChangeBillingAddress()
                .clickUseAsMyDefaultShippingAddressCheckbox()
                .clickSaveAddressButton()
                .verifySavedShippingAddressContact(firstName, lastName, address, city, phoneNumber);
    }

    @TmsLink("TC_Address_Book_Test_007")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser", description = "Change shipping to billing address contact")
    public void test07ChangeShippingAddressToBilling() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String city = testData.getCity();
        String address = testData.getHomeAddress();
        String phoneNumber = testData.getPhoneNumber();
        UI.AddressBook()
                .clickAddNewAddressButton();
        UI.AddressBook()
                .clearFirstName()
                .enterFirstName(firstName)
                .clearLastName()
                .enterLastName(lastName)
                .enterTelephone(phoneNumber)
                .enterStreetAddress1(address)
                .enterCity(city)
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickUseAsMyDefaultShippingAddressCheckbox()
                .clickSaveAddressButton()
                .verifySavedShippingAddressContact(firstName, lastName, address, city, phoneNumber)
                .clickChangeShippingAddress()
                .clickUseAsMyDefaultBillingAddressCheckbox()
                .clickSaveAddressButton()
                .verifySavedBillingAddressContact(firstName, lastName, address, city, phoneNumber);
    }

    @TmsLink("TC_My_Address_Book_Test_008")
    @Feature("DermPRO Address book page")
    @Test(groups = "signedUser", description = "Address book test delete address contact")
    public void test08DeleteAddress() {
        if (UI.AddressBook().isAddressToDeletePresent()) {
            UI.AddressBook()
                    .clickDeleteAddress()
                    .clickOkButton()
                    .verifyAddressBookMessage("You deleted the address.");
        }
    }


    @AfterClass
    public void after() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickAddressBook();
        UI.AddressBook()
                .deleteAllAddresses();
    }

}
