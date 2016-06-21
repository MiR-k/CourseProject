package regard.tests;

import org.testng.annotations.Test;
import regard.UI.forms.BasketForm;
import regard.UI.forms.MainPageForm;
import webdriver.BaseTest;

public class RegardBasketTest extends BaseTest {

    @Test
    public void runTest() {
        logStep(1);
        MainPageForm mainPage = new MainPageForm();

        logStep(2);
        mainPage.addBasketFrom("Хиты");
        mainPage.navigateTo("оформление");

        logStep(3);
        BasketForm basket = new BasketForm();

        logStep(4);
        basket.checkForID(mainPage.getLutForBasket());

        logStep(5);
        basket.removeAll();
    }
}
