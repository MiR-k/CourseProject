package regard.tests;

import org.testng.annotations.*;
import regard.UI.CatalogFilter;
import regard.UI.LeftMenuTypes;
import regard.UI.forms.*;
import regard.UI.menu.LeftMenuForm;
import webdriver.BaseTest;


public class RegardCompareTest extends BaseTest {

    @Test
    @Parameters({"LowerPrice","TopPrice", "ToCompare"})
    public void runTest(String lowPrice, String topPrice, int compare) {
        logStep(1);
        MainPageForm mainPageForm = new MainPageForm();

        logStep(2);
        LeftMenuForm leftMenu = new LeftMenuForm();
        leftMenu.allSubItem(LeftMenuTypes.VideoCards.toString());
        CatalogForm catalog = new CatalogForm();

        logStep(3);
        catalog.searchForTXB(CatalogFilter.Price.toString(),lowPrice,topPrice);
        catalog.checkInput(CatalogFilter.Price.toString(),lowPrice,topPrice);

        logStep(4);
        catalog.addCompare(compare);

        logStep(5);
        CompareForm compareForm = new CompareForm();
        compareForm.checkForID(catalog.getListConfig());

        logStep(6);
        compareForm.deleteItems(2,4);

    }
}
