package data;

import com.github.javafaker.Faker;
import data.bean.Store;

import java.time.LocalDate;
import java.util.Date;

public class TestData {

    private Faker faker;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private String secondaryAddress;
    private String state;
    private String city;
    private String country;
    private String zipCode;
    private String masterCardCreditCardNumber;
    private String visaCreditCardNumber;
    private String month;
    private String year;
    private String sentence;
    private String secondSentence;

    //from Store object
    private Store store;
    private String firstProductName;
    private String secondProductName;
    private String thirdProductName;
    private String menuVariable;

    public TestData() {
        faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.emailAddress = Long.toString(new Date().getTime()) + "@mailinator.com";
        this.password = faker.internet().password(11, 15);
        this.phoneNumber = "3809" + faker.number().numberBetween(10000000, 99999999);
        this.homeAddress = faker.address().fullAddress();
        this.city = faker.address().city();
        this.country = "United States";
        this.zipCode = "12345";
        this.state = "Alaska";
        this.secondaryAddress = faker.address().secondaryAddress();
        this.masterCardCreditCardNumber = "5555555555554444";
        this.visaCreditCardNumber = "4012888888881881";
        this.month = LocalDate.now().getMonth().toString().toLowerCase();
        this.year = String.valueOf(LocalDate.now().getYear());
        this.sentence = faker.lorem().sentence();
        this.secondSentence = faker.lorem().paragraph();
    }

    public void setStore(Store store) {
        this.store = store;
        if (store != null) {
            this.firstProductName = store.getProductsNames().get(0);
            this.secondProductName = store.getProductsNames().get(1);
            this.thirdProductName = store.getProductsNames().get(2);
            this.menuVariable = store.getMainMenuCategories().get(0).getName();
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getMasterCardCreditCardNumber() {
        return masterCardCreditCardNumber;
    }

    public String getVisaCreditCardNumber() {
        return visaCreditCardNumber;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSentence() {
        return sentence;
    }

    public String getSecondSentence() {
        return secondSentence;
    }

    public Store getStore() {
        return store;
    }

    public String getFirstProductName() {
        return firstProductName;
    }

    public String getSecondProductName() {
        return secondProductName;
    }

    public String getThirdProductName() {
        return thirdProductName;
    }

    public String getMenuVariable() {
        return menuVariable;
    }


    @Override
    public String toString() {
        return "TestData{" +
                "faker=" + faker +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", secondaryAddress='" + secondaryAddress + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", masterCardCreditCardNumber='" + masterCardCreditCardNumber + '\'' +
                ", visaCreditCardNumber='" + visaCreditCardNumber + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", sentence='" + sentence + '\'' +
                ", secondSentence='" + secondSentence + '\'' +
                ", firstProductName='" + firstProductName + '\'' +
                ", store='" + store + '\'' +
                ", secondProductName='" + secondProductName + '\'' +
                ", thirdProductName='" + thirdProductName + '\'' +
                ", menuVariable='" + menuVariable + '\'' +
                '}';
    }
}
