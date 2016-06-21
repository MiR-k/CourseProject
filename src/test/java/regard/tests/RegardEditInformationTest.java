package regard.tests;

import org.testng.annotations.*;
import regard.UI.PersonalAreaCategory;
import regard.UI.forms.LoginForm;
import regard.UI.forms.MainPageForm;
import regard.UI.forms.PersonalAreaForm;
import webdriver.BaseTest;

public class RegardEditInformationTest extends BaseTest {

    @Test
    @Parameters({"USER","PASSWORD"})
    public void runTest(String loginName, String password) {

        logStep(1,2);
        MainPageForm mainPageForm = new MainPageForm();
        LoginForm loginForm = new LoginForm();
        loginForm.login(loginName, password);

        logStep(3);
        loginForm.lfNavigate("кабинет");
        PersonalAreaForm personalAreaForm = new PersonalAreaForm();

        logStep(4);
        personalAreaForm.navigatePersonalArea(PersonalAreaCategory.Personal.toString());

        logStep(5);
        personalAreaForm.editInformation(loginName,"Имя");

        logStep(6);
        loginForm.reLogin(loginName, password);

        logStep(7);
        loginForm.checkVisibleName(personalAreaForm.getNewName());
    }
}
