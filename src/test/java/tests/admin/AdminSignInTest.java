package tests.admin;

import data.bean.AdminAccount;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class AdminSignInTest extends AdminBaseTest {

    @TmsLink("TC_Admin_001")
    @Feature("DermPRO Admin")
    @Test(groups = {"admin"}, description = "Admin Sign In")
    public void test01SuccessfulSignIn() {
        AdminAccount adminAccount = testData.getStore().getAdminAccount();

        UI_Admin.AdminSignIn()
                .enterUserName(adminAccount.getUserName())
                .enterPassword(adminAccount.getPassword())
                .clickSignIn();
        UI_Admin.AdminDashboard()
                .closePopupMessages()
                .verifyAccountDashboardInformation();
    }

}
