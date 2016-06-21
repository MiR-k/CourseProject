package regard.UI.menu;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import regard.UI.*;
import webdriver.elements.Label;

import java.util.List;

public class MainMenuForm extends BaseForm{

    /**
     * Constructor MainMenuForm
     */
    public MainMenuForm(){
        super(By.id("welcome"), "Regard.ru");
    }

    /**
     * Check Search result and print console
     * @param queryString string
     *                    query of string
     * @param listResult List<>
     *                   result value search
     */
    public void checkSearch(String queryString, List<String> listResult){
        int numFull = 0;
        int numPart = 0;
        int numMiss;
        for (String resultPart: listResult){
            if (resultPart.contains(queryString)){
                numFull++;
            } else if (numberCoincidences(queryString,resultPart)){
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
     *
     * @param queryString String
     *                     query of string
     * @param partResult String
     *                   part of result search
     * @return boolean
     *          whether or not the line
     */
    public boolean numberCoincidences (String queryString, String partResult){
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

    public void getChoto(String choto){
        info("0");
        Label lbl = new Label(By.xpath("//div[@id='login-form'] //span[@id='username']"),"Personal");
        info("1");
        Assert.assertTrue(lbl.getText().equals(choto));
        info(String.format("%s equal %s", choto, lbl.getText()));
    }

}
