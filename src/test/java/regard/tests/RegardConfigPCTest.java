package regard.tests;

import org.testng.annotations.*;
import regard.UI.TopMenuElement;
import regard.UI.forms.BasketForm;
import regard.UI.forms.ConfigPCForm;
import regard.UI.forms.MainPageForm;
import regard.UI.menu.TopMenuForm;
import webdriver.BaseTest;

public class RegardConfigPCTest extends BaseTest{

    @Test
    @Parameters({"CPU","Case","Monitor"})
    public void runTest(String cPU, String casePC, String monitor){

        logStep(1);
        MainPageForm mainPage = new MainPageForm();

        logStep(2);
        TopMenuForm topMenu = new TopMenuForm();
        topMenu.navigateMenu(TopMenuElement.Configurator.toString());

        logStep(3);
        ConfigPCForm configPC = new ConfigPCForm();
        configPC.selectCPU(cPU);

        logStep(4,5);
        configPC.addToConfig(cPU);
        configPC.addToConfig(monitor);
        configPC.addToConfig(casePC);
        configPC.configAndCheckOut();

        logStep(6);
        BasketForm basket = new BasketForm();
        basket.checkForID(configPC.getListConfig());

    }
}
