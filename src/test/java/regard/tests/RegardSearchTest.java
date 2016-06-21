package regard.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import regard.UI.forms.MainPageForm;
import regard.UI.forms.SearchForm;
import webdriver.BaseTest;

public class RegardSearchTest extends BaseTest{

    @Test
    @Parameters({"QUERY"})
    public void runTest(String query){
        logStep(1);
        MainPageForm mainPageForm = new MainPageForm();

        logStep(2,3);
        SearchForm searchForm = new SearchForm();
        searchForm.searchQuery(query);

        logStep(4);
        searchForm.checkAndPrint(query.toLowerCase());
    }

}
