package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.*;

public class AccountInformationTest extends BaseTest { //TODO there is some problem with Logout after random actions

    private String newPassword;
    private String newEmail;
    private boolean nameWasChanged = false;
    private boolean emailAndPasswordWasChanged = false;
    private boolean dateOfBirthWasChanged = false;
    private boolean genderWasChanged = false;
    String newFirstName;
    String newLastName;
    private String oldFirstName;
    private String oldLastName;
    private String currentDateOfBirth;
    private String currentGender;

    private String currentPassword;
    private String currentEmail;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            currentPassword = testData.getStore().getAccountForUpdate().getPassword();
            currentEmail = testData.getStore().getAccountForUpdate().getEmail();

            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(currentEmail, currentPassword);
                isLogged = true;
            }

            UI.Header()
                    .accountClick();
            oldFirstName = UI.AccountDashboard().getFirstName();
            oldLastName = UI.AccountDashboard().getLastName();
            UI.Account()
                    .clickAccountInformation();

            currentDateOfBirth = UI.AccountInformation().getCurrentDateOfBirth();
            currentGender = UI.AccountInformation().getCurrentGenderValue();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!UI.AccountInformation().isOnEditAccountInformation()) {
                UI.Account()
                        .clickAccountInformation();
            }

            if (UI.SignIn().isOnLoginPage()) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(currentEmail, currentPassword);
                UI.Header()
                        .accountClick();
                UI.Account()
                        .clickAccountInformation();
            }

        }
    }

    @TmsLink("TC_Account_Information_001")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information all fields are empty")
    public void test01EmptyAllFields() {
        UI.AccountInformation()
                .clearFirstName()
                .clearLastName()
                .clearDateOfBirth()
                .clickDatePickerCloseButton()
                .checkChangeEmailCheckbox()
                .checkChangePasswordCheckbox()
                .clearEmail()
                .clickSaveButton()
                .verifyEmptyFields();
    }

    @TmsLink("TC_Account_Information_002")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Verify Go back redirection")
    public void test02VerifyGoBackRedirection() {
        UI.AccountInformation()
                .clickGoBackButton();
        UI.AccountDashboard()
                .verifyAccountDashboardPage();
    }

    @TmsLink("TC_Account_Information_003")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Verify Gender update")
    public void test03VerifyGenderUpdate() {
        String gender = "Not Specified";
        UI.AccountInformation()
                .selectGender(gender)
                .clickSaveButton();
        UI.Account()
                .clickAccountInformation();
        UI.AccountInformation()
                .verifyGenderValue(gender);
        genderWasChanged = true;
    }

    @TmsLink("TC_Account_Information_004")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Verify Date Of Birth update")
    public void test04VerifyDateOfBirthUpdate() {
        String todayDate = getCurrentDate();
        UI.AccountInformation()
                .clearDateOfBirth()
                .enterDateOfBirth(todayDate)
                .clickDatePickerCloseButton()
                .clickSaveButton();
        UI.Account()
                .clickAccountInformation();
        UI.AccountInformation()
                .verifyDateOfBirth(todayDate);
        dateOfBirthWasChanged = true;
    }

    @TmsLink("TC_Account_Information_005")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Verify Change Email section is not displayed when <<Change Email>> checkbox is disabled")
    public void test05EmailSectionVisibility() {
        UI.AccountInformation()
                .verifyChangeEmailSectionNotVisibleIfCheckboxDisabled();
    }

    @TmsLink("TC_Account_Information_006")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Verify Change Password section is not displayed when <<Change Password>> checkbox is disabled")
    public void test06PasswordSectionVisibility() {
        UI.AccountInformation()
                .verifyChangePasswordSectionNotVisibleIfCheckboxDisabled();
    }


    @TmsLink("TC_Account_Information_007")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information short password")
    public void test07ShortPassword() {
        UI.AccountInformation()
                .checkChangePasswordCheckbox()
                .enterCurrentPassword(currentPassword)
                .enterNewPassword("123")
                .verifyNewPassword("Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");
    }

    @TmsLink("TC_Account_Information_008")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information weak password")
    public void test08WeakPassword() {
        UI.AccountInformation()
                .checkChangePasswordCheckbox()
                .enterCurrentPassword(currentPassword)
                .enterNewPassword("1928abcd")
                .verifyNewPassword("Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.");
    }

    @TmsLink("TC_Account_Information_009")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information invalid email")
    public void test09InvalidEmail() {
        UI.AccountInformation()
                .checkChangeEmailCheckbox()
                .clearEmail()
                .enterEmail("qwertyuiop")
                .clickSaveButton()
                .verifyEmail("Please enter a valid email address.");
    }

    @TmsLink("TC_Account_Information_010")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information new and confirmation passwords are not lonely")
    public void test10NewAndConfirmationPasswordAreNotTheSame() {
        UI.AccountInformation()
                .checkChangePasswordCheckbox()
                .enterCurrentPassword(currentPassword)
                .enterNewPassword("1928abcdZXCV")
                .enterConfirmationPasswordInput("9870ASDFmnbv")
                .clickSaveButton()
                .verifyPasswordConfirmation("Please enter the same value again.");
    }

    @TmsLink("TC_Account_Information_011")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information current password does not correct")
    public void test11CurrentPasswordDoesNotCorrect() {
        UI.AccountInformation()
                .checkChangeEmailCheckbox()
                .uncheckChangePasswordCheckbox()
                .clearEmail()
                .enterEmail(currentEmail)
                .enterCurrentPassword("ZXClkj93845-")
                .clickSaveButton();
        UI.AccountInformation()
                .verifyCurrentPassword("The password doesn't match this account.");
    }

    @TmsLink("TC_Account_Information_012")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "Account information successful change first name and last names")
    public void test12SuccessfulDataChange() {
        newFirstName = testData.getFirstName() + "A";
        newLastName = testData.getLastName() + "A";
        UI.AccountInformation()
                .uncheckChangePasswordCheckbox()
                .uncheckChangeEmailCheckbox()
                .clearFirstName()
                .enterFirstName(newFirstName)
                .clearLastName()
                .enterLastName(newLastName)
                .clickSaveButton();
        UI.Account()
                .verifyAccountDashboardInformation(newFirstName, newLastName, currentEmail);
        nameWasChanged = true;
        UI.Account()
                .clickAccountInformation();
    }

    @TmsLink("TC_Account_Information_013")
    @Feature("DermPRO Account_Information page")
    @Test(groups = "signedUser", description = "account information successful change email and password")
    public void test13ChangeEmailAndPassword() {
        newPassword = "LKJHzxcv3928171" + testData.getPassword();
        newEmail = "A" + testData.getEmailAddress();
        UI.AccountInformation()
                .checkChangeEmailCheckbox()
                .clearEmail()
                .enterEmail(newEmail)
                .checkChangePasswordCheckbox()
                .enterCurrentPassword(currentPassword)
                .enterNewPassword(newPassword)
                .enterConfirmationPasswordInput(newPassword)
                .clickSaveButton();
        UI.Account()
                .verifyEmail(newEmail);
        emailAndPasswordWasChanged = true;

        UI.Header()
                .logOutClick();
        UI.Header()
                .logInClick();
        UI.SignIn()
                .enterEmail(newEmail)
                .enterPassword(newPassword)
                .clickSignInButton();
    }

    private void navigateToAccountInformation() {
        if (!UI.AccountInformation().isOnEditAccountInformation()) {
            if (UI.SignIn().isOnLoginPage())
                UI.SignIn()
                        .quickLogin(currentEmail, currentPassword);
            UI.Account()
                    .clickAccountInformation();
        }
    }

    @AfterClass
    public void after() {
        if (emailAndPasswordWasChanged) {
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickAccountInformation();
            UI.AccountInformation()
                    .checkChangeEmailCheckbox()
                    .clearEmail()
                    .enterEmail(currentEmail)
                    .enterCurrentPassword(newPassword)
                    .clickSaveButton();
            UI.Account()
                    .clickAccountInformation();
            UI.AccountInformation()
                    .checkChangePasswordCheckbox()
                    .enterCurrentPassword(newPassword)
                    .enterNewPassword(currentPassword)
                    .enterConfirmationPasswordInput(currentPassword)
                    .clickSaveButton();
        }
        if (nameWasChanged) {
            navigateToAccountInformation();
            UI.AccountInformation()
                    .clearFirstName()
                    .enterFirstName(oldFirstName)
                    .clearLastName()
                    .enterLastName(oldLastName);
            if (UI.AccountInformation().isOnEditAccountInformation())
                UI.AccountInformation().clickSaveButton();
            if (UI.SignIn().isOnLoginPage()) {
                UI.SignIn()
                        .quickLogin(currentEmail, currentPassword);
                if (!UI.AccountInformation().isOnEditAccountInformation())
                    UI.Account()
                            .clickAccountInformation();
            }
            if (!UI.AccountDashboard().isOnAccountDashboard())
                UI.AccountInformation()
                        .clickSaveButton();
        }
        if (dateOfBirthWasChanged) {
            navigateToAccountInformation();
            if (!UI.AccountInformation().getCurrentDateOfBirth().equals(currentDateOfBirth)) {
                UI.AccountInformation()
                        .clearDateOfBirth()
                        .enterDateOfBirth(currentDateOfBirth)
                        .clickDatePickerCloseButton()
                        .clickSaveButton();
            }
        }
        if (genderWasChanged || StringUtils.isEmpty(currentGender)) {
            navigateToAccountInformation();
            if (!UI.AccountInformation().getCurrentGenderValue().equals(currentGender)) {
                UI.AccountInformation()
                        .selectGender(currentGender)
                        .clickSaveButton();
            }
        }
    }

}
