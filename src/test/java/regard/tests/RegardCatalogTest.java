package regard.tests;

import org.testng.annotations.Test;
import regard.UI.Category;
import regard.UI.LeftMenuBrands;
import regard.UI.SortName;
import regard.UI.forms.*;
import regard.UI.menu.*;
import webdriver.BaseTest;

public class RegardCatalogTest extends BaseTest {

    @Test
    public void runTest() {
        logStep(1);
        MainPageForm mainPageForm = new MainPageForm();

        logStep(2);
        LeftMenuForm leftMenuForm = new LeftMenuForm();
        leftMenuForm.selectCategory(Category.Brand.toString());

        logStep(3);
        leftMenuForm.leftNavigate(LeftMenuBrands.Samsung.toString());

        logStep(4);
        CatalogForm catalogForm = new CatalogForm();
        catalogForm.sortBy(SortName.Price.toString());

        logStep(5);
        catalogForm.checkSortPrice();
    }
}
