package regard.UI.forms;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import java.util.ArrayList;
import java.util.List;

public class MainPageForm  extends BaseForm {

    private static String locatorShowcase = "//h1[contains(text(),\"%s\")]/..";
    private static String locatorBtnBasket = "//a[@class='cart']";
    private static String locatorFollow = "/following-sibling::div";
    private static String locatorID = "//div[@class='code']";
    private static String locatorBasket = "//div[contains(@title,\"%s\")]";
    private Label lblNumItemBasket = new Label(By.id("global_basket_kol"),"Label Item in Basket");
    List<String> lutForBasket = new ArrayList<String>();

    public MainPageForm(){
        super(By.id("welcome"), "Regard.ru");
    }

    /**
     * Method add all item from block
     *
     * @param showCase String
     *                 Name block for Main Page
     */
    public void addBasketFrom(String showCase){
        Label lblShowcase = new Label(By.xpath(String.format(locatorShowcase,showCase)),String.format("Label ",showCase));
        lblShowcase.isPresent();
        String txtButtonItem = String.format(locatorShowcase+locatorFollow+locatorBtnBasket,showCase);
        List<WebElement> listItem = findElements(By.xpath(txtButtonItem));
        for (int i = 0; i<listItem.size();i++){
            Label lblTempID = new Label(By.xpath(String.format(locatorShowcase+locatorFollow+"/div[%s]"+locatorID,showCase,i+1)),String.format("%s item",i));
            lutForBasket.add(lblTempID.getText());
            Button temp = new Button(By.xpath(String.format(locatorShowcase+locatorFollow+"/div[%s]"+locatorBtnBasket,showCase,i+1)),String.format("Add basket %s item",lutForBasket.get(i)));
            scrollDown(temp.getLocator());
            temp.waitAndClick();
            browser.waitForPageToLoad();
            lblNumItemBasket.waitAndAssertIsPresent();
        }
        Assert.assertEquals(lblNumItemBasket.getText(), Integer.toString(listItem.size()));
        info(String.format("Added basket: %s item's",lblNumItemBasket.getText()));
    }

    /**
     * Method Navigate to
     * @param name String
     *             name item
     */
    public void navigateTo(String name){
        name = name.substring(0,name.length()-1);
        Label lblBasket = new Label(By.xpath(String.format(locatorBasket,name)),String.format("Label %s",name));
        lblBasket.waitAndClick();
    }

    /**
     * method returns a collection added to cart
     * @return List String
     *
     */
    public List<String> getLutForBasket(){
        return lutForBasket;
    }
}
