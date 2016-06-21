package regard.tests;

import org.testng.annotations.*;
import regard.UI.PersonalAreaCategory;
import regard.UI.forms.LoginForm;
import regard.UI.forms.MainPageForm;
import regard.UI.forms.PersonalAreaForm;
import webdriver.BaseTest;


public class RegardLoginTest extends BaseTest {

    @Test
    @Parameters({"USER","PASSWORD"})
    public void runTest(String loginName, String password) {
        logStep(1);
        MainPageForm mainPageForm = new MainPageForm();

        logStep(2);
        LoginForm loginForm = new LoginForm();
        loginForm.login(loginName,password);

        logStep(3);
        loginForm.lfNavigate("кабинет");

        logStep(4);
        PersonalAreaForm personalAreaForm = new PersonalAreaForm();
        personalAreaForm.navigatePersonalArea(PersonalAreaCategory.Personal.toString(),"Personal information");

        logStep(5);
        personalAreaForm.checkInformation(loginName, "Логин");
    }

}
