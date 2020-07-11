package tests;

import io.qameta.allure.Feature;

import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignUpTest extends BaseTest {

    private final String gender = "Male";

    @TmsLink("TC_Sign_Up_001")
    @Feature("DermPRO Sign Up page")
    @Test(description = "Empty fields test")
    public void test01EmptyFields() throws Exception {
        UI.Header().signUpClick();
        UI.SignUp()
                .clearDate()
                .clickDatePickerCloseButton()
                .clickSignUpButton()
                .verifyEmptyFields();
    }


    @TmsLink("TC_Sign_Up_002")
    @Feature("DermPRO Sign Up page")
    @Test(description = "Invalid fields test")
    public void test02InvalidFields() throws Exception {
        UI.Header().signUpClick();
        UI.SignUp()
                .enterFirstName("a")
                .enterLastName("b")
                .selectGender(gender);
        UI.SignUp()
                .enterEmailAddress("c")
                .enterPassword("12")
                .enterPasswordConfirmation("21")
                .clickSignUpButton()
                .verifyInvalidFields();
    }

    @TmsLink("TC_Sign_Up_003")
    @Feature("DermPRO Sign Up page")
    @Test(description = "Weak password test")
    public void test03InvalidPassword() {
        String invalidPassword = "1234567890";
        UI.Header().signUpClick();
        UI.SignUp()
                .enterPassword(invalidPassword)
                .clickSignUpButton()
                .verifyInvalidPassword();
    }

    @TmsLink("TC_Sign_Up_004")
    @Feature("DermPRO Sign Up page")
    @Test(description = "Sign up invalid date")
    public void test04SignUpInvalidDate() throws Exception {
        String password = "ABcde1234567890";
        String invalidDate = "12345678";
        UI.Header().signUpClick();
        UI.SignUp()
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .clearDate()
                .enterDate(invalidDate)
                .clickDatePickerCloseButton()
                .selectGender(gender)
                .enterEmailAddress(testData.getEmailAddress())
                .enterPassword(password)
                .enterPasswordConfirmation(password)
                //  .enterUserCaptcha("123") TODO captcha
                .clickSignUpButton()
                .verifyInvalidDate();
    }

    @TmsLink("TC_Sign_Up_005")
    @Feature("DermPRO Sign Up page")
    @Test(groups = "signedUser", description = "Sign up successful flow")
    public void test05SignUpSuccessfulFlow() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/Y");
        LocalDate date = LocalDate.now();
        String dateOfBirth = date.minusYears(17).format(formatter);
        String password = "ABcde1234567890";
        UI.Header().signUpClick();
        UI.SignUp()
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .clearDate()
                .enterDate(dateOfBirth)
                .clickDatePickerCloseButton()
                .selectGender(gender)
                .enterEmailAddress(testData.getEmailAddress())
                .enterPassword(password)
                .enterPasswordConfirmation(password)
                //  .enterUserCaptcha("123") TODO captcha
                .clickSignUpButton()
                .verifySuccessfulRegistration(testData.getStore().getName());
    }

}
