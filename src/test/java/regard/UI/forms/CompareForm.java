package regard.UI.forms;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.List;

public class CompareForm extends BaseForm{

    private static String locDeleting = "//tbody//td [%s] //a[@class='remove']";
    private Label lblID = new Label(By.xpath("//td[@class='code']"),"Label ID");

    //because 1 it's Head Table, 2 - first item's in table
    private int numberItemList = 2;
    private List<WebElement> listItem;

    /**
     * Constructor CompareForm
     */
    public CompareForm(){
        super(By.xpath("//h1[contains(text(),\"Сравнение\")]"), "Compare Form");
    }

    /**
     * Method deleting items by indexOf to indexTo
     * @param indexOf int
     *                 first index for delete
     * @param indexTo int
     *                 last index for delete
     */
    public void deleteItems(int indexOf, int indexTo){
        Button btnDelete;
        indexTo++;
        if (numberItemList != indexOf){
            numberItemList = indexOf;
        }
        do {
            btnDelete = new Button(By.xpath(String.format(locDeleting,numberItemList)),String.format("Button del %s",numberItemList));
            if (btnDelete.isPresent()){
                if (numberItemList < indexTo){
                    deleteItem(numberItemList);
                    indexTo--;
                    numberItemList--;
                }
            }
            numberItemList++;
            if (numberItemList > indexTo){
                break;
            }
        } while (btnDelete.isPresent());
    }

    /**
     * Method delete one item by index
     * @param indexItem int
     *                  index item
     */
    public void deleteItem(int indexItem){
        Button btnDelete;
        listItem = lblID.findElements();
        int startIndex = listItem.size();
        btnDelete = new Button(By.xpath(String.format(locDeleting,indexItem+1)),String.format("Item index %s delete",indexItem));
        if (btnDelete.isPresent()){
            btnDelete.click();
        }
        listItem = lblID.findElements();
        Assert.assertTrue((startIndex-1)==listItem.size());
    }

    /**
     * Method checks the goods ID
     *
     * @param listParts List
     *                  List element from Configurator or Catalog
     */
    public void checkForID(List<String> listParts){
        checkFor(listParts,lblID.getLocator());
    }

}
