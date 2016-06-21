package regard.UI.forms;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import java.util.List;

public class BasketForm extends BaseForm{

    private Button btnEraseAll = new Button(By.xpath("//a[contains(@title,\"Очистить\")]"), "Erase all item");
    private Label lblNullBasket = new Label(By.xpath("//h3[@class='alarm']"),"Label Null Basket");
    private Label lblCheckout = new Label(By.xpath("//div[contains(text(),\"Оформление\")]"),"Label checkout");
    private Label lblID = new Label(By.xpath("//td[@class='t1']"));

    /**
     * Constructor BasketForm
     */
    public BasketForm(){
        super(By.xpath("//h1[contains(text(),\"Корзина\")]"), "Basket Area");
    }

    /**
     * Method deleting one item by Name
     * @param nameItem String
     *                 Name item for deleting
     */
    public void deleteItem(String nameItem){
        Button btn = new Button(String.format("[%s]//a[contains(@title,\"Удалить\")]",nameItem),String.format("Button del %s",nameItem));
        btn.waitAndClick();
    }

    /**
     * Method remove all item from basket
     */
    public void removeAll(){
        btnEraseAll.waitAndClick();
        alertAccept();
        checkNull();
    }

    /**
     * Method checks the goods ID
     *
     * @param listParts List
     *                  List element from Configurator or Catalog
     */
    public void checkForID(List<String> listParts){
        lblCheckout.assertIsPresent();
        checkFor(listParts,lblID.getLocator());
    }

    /**
     * Method check basket if null
     */
    public void checkNull(){
        lblNullBasket.waitAndAssertIsPresent();
    }
}
