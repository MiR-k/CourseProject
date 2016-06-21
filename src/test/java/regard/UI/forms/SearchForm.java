package regard.UI.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;
import java.util.List;

public class SearchForm extends BaseForm {

    private TextBox txtSearch = new TextBox(By.id("query"), "Input search");
    private Button btnSubmit = new Button(By.xpath("//div[@id='search-block'] //button"), "Button submit");

    private Label lblItemSeacrh = new Label(By.xpath("//div[@id='hits'] //a[@class='header']"),"Label item search");
    private Button btnToCompare = new Button(By.xpath("//img[contains(@src,\"compare1\")]/.."),"Button to compare");

    public SearchForm(){
        super(By.id("search-block"),"Search Form Form");
    }

    /**
     * Method input and submit query
     * @param query String
     *                     text query
     */
    public void searchQuery(String query){
        txtSearch.setText(query);
        btnSubmit.waitAndClick();
    }

    /**
     * Check Search result and print console
     * @param queryString string
     *                    query of string
     */
    public void checkAndPrint(String queryString){
        List<WebElement> listResult = lblItemSeacrh.findElements();
        int numFull = 0;
        int numPart = 0;
        int numMiss;
        for (WebElement resultPart: listResult){
            if (resultPart.getText().toLowerCase().contains(queryString)){
                numFull++;
            } else if (verifEntry(queryString,resultPart.getText().toLowerCase())){
                numPart++;
            }
        }
        numMiss = listResult.size() - numPart - numFull;
        printSearchResult("Full",numFull, listResult.size());
        printSearchResult("Partially", numPart, listResult.size());
        printSearchResult("No", numMiss,listResult.size());
    }

    /**
     *  Print search result
     * @param prefixx String
     *                prefixx for message
     * @param parts int
     *              number of parts
     * @param resultAll int
     *                  all number
     */
    private void printSearchResult(String prefixx, int parts, int resultAll){
        String tempMessage = String.format("%s matches %s of %s ", prefixx, parts, resultAll);
        info(tempMessage);
    }

    /**
     * method verify string
     * @param queryString String
     *                     query of string
     * @param partResult String
     *                   part of result search
     * @return boolean
     *          whether or not the line
     */
    public boolean verifEntry (String queryString, String partResult){
        String queryPart[] = queryString.split(" ");
        String tempString;
        int matches =0;
        for(int i = 0; i < queryPart.length; i++) {
            if ((queryPart[i].length() < 1 && i != 0)) {
                tempString = queryPart[i] + queryPart[i-1];
            } else {
                tempString = queryPart[i];
            }
            if (partResult.contains(tempString.substring(0,tempString.length()))){
                matches++;
            }
        }
        return (matches > 0);
    }
}

