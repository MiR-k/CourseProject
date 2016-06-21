package regard.UI.forms;


import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;
import java.util.ArrayList;
import java.util.List;

public class CatalogForm extends BaseForm {

    private static String locSort = "//div[@class='sort'] //a[contains(text(),\"%s\")]";
    private static String toXPathInputMin = "//span[contains(text(),\"%s\")]/.. /following-sibling::div //input[contains(@id,\"min\")]";
    private static String toXPathInputMax = "//span[contains(text(),\"%s\")]/.. /following-sibling::div //input[contains(@id,\"max\")]";
    private static String locFilter = "//div[contains(text(),\"%s\")] /following-sibling::a";
    private static String locatorBtnCompare = "//div[@class='block'][%s]//img[contains(@src,\"compare\")]/..";
    private static String locatorLblID = "/../preceding-sibling::div/div[@class='code']";

    private Label lblShow = new Label(By.id("view-mode"));
    private Label lblProductName = new Label(By.xpath("//a[@class='header']"), "Item header");
    private Label lblPrice = new Label(By.xpath(String.format("//div[@class='%sprice']",showCheck())), "Item header");
    private Label lblFinger = new Label(By.xpath("//span[@id=\"finger\"]"),"Finger");

    private Button btnAcept = new Button(By.xpath("//a[@id='b_apply_filter']"),"Button Accept");

    private List<String> listConfig = new ArrayList<String>();

    public CatalogForm() {
        super(By.xpath("//a[@href='/catalog/']"), "Catalog Form");
    }

    /**
     * Method sorts of
     * @param nameSort String
     *                 Sorts attribute
     */
    public void sortBy(String nameSort){
        Label lblSort = new Label(By.xpath(String.format(locSort, nameSort)), String.format("Sort by %s",nameSort));
        lblSort.click();
    }

    /**
     * Method check sort by Price
     * and print log
     */
    public void checkSortPrice(){
        List<Integer> intPrice = new ArrayList<Integer>();
        String subStrPrice;
        List<WebElement> listPrice = lblPrice.findElements();
        for (WebElement item:listPrice){
            subStrPrice  = item.getText().trim().substring(0,item.getText().trim().indexOf(" "));
            intPrice.add(Integer.parseInt(subStrPrice));
        }
        for(int i=1;i < intPrice.size();i++){
            Assert.assertTrue((intPrice.get(i)>=intPrice.get(i)));
            info(String.format("Correct %d. price: %s >= price %s",i, intPrice.get(i),intPrice.get(i-1)));
        }
        info("Total item's: " + intPrice.size());
    }

    /**
     * This method search and send name, setpoint
     *
     * @param nameMark String
     *                name fields
     * @param inputMin int
     *               lower limit
     * @param inputMax int
     *               upper limit
     */
    public void searchForTXB(String nameMark, String inputMin, String inputMax){
        Label lblName = new Label(By.xpath(String.format("//span[contains(text(),\"%s\")]", nameMark)),nameMark);
        lblName.waitAndClick();
        if(Integer.valueOf(inputMax) !=0){
            TextBox txbInputMin = new TextBox(By.xpath(String.format(toXPathInputMax,nameMark)), nameMark+" lower to");
            txbInputMin.setText(inputMax);
            checkInputFilter(inputMax,txbInputMin);
            lblFinger.waitForIsElementPresent();
        }
        if(Integer.valueOf(inputMin) !=0){
            TextBox txbInputMax = new TextBox(By.xpath(String.format(toXPathInputMin,nameMark)), nameMark+" upper to");
            txbInputMax.setText(inputMin);
            checkInputFilter(inputMin,txbInputMax);
            lblFinger.waitForIsElementPresent();
        }
        btnAcept.waitAndClick();
    }

    /**
     *
     * @param nameMark String
     *
     * @param inputMin String
     *
     * @param inputMax String
     *
     */
    public void checkInput(String nameMark, String inputMin, String inputMax){
        Label lblFilter = new Label(By.xpath(String.format(locFilter, nameMark)),String.format("Filter: %s",nameMark));
        Assert.assertTrue(lblFilter.getText().contains(inputMin));
        Assert.assertTrue(lblFilter.getText().contains(inputMax));
        info(String.format(" All is well. Filter %s: %s-%s",nameMark,inputMin,inputMax));
    }

    /**
     * Check input information is present
     * @param input int
     *              information input
     * @param txbTemp TextBox
     *                textbox
     */
    private void checkInputFilter(String input,TextBox txbTemp){
        while (txbTemp.getText().contains(input)){
            txbTemp.setText("");
            txbTemp.setText(input);
            browser.waitForPageToLoad();
        }
    }

    /**
     * Method add all item from block
     *
     * @param numItem int
     *                 number element to add compare
     */
    public void addCompare(int numItem){
        Button btnToCompare;
        int i = 1;
        Label lblBlock;
        Label lblID;
        do{
            btnToCompare = new Button(By.xpath(String.format(locatorBtnCompare,i)),String.format("Button compare %s",i));
            lblBlock = new Label(By.xpath(String.format("//div[@class='block'][%s]",i)),String.format("Block %s",i));
            lblID = new Label(By.xpath(String.format(locatorBtnCompare+locatorLblID,i)),String.format("Block %s",i));
            i++;
            if (btnToCompare.isPresent()){
                btnToCompare.click();
                listConfig.add(lblID.getText().substring(lblID.getText().indexOf(" ")));
            }
            if (i > numItem){
                break;
            }
        } while (lblBlock.isPresent());
        btnToCompare.click();
    }

    /**
     * Method check visible selected display method
     * and return prefix if needed
     * @return String
     *          prefix if needed
     */
    public String showCheck(){
        if (lblShow.getText().contains("списком")){
            return "list-";
        } else {
            return "";
        }
    }

    /**
     * method return list item add to configurator
     * @return List String
     */
    public List<String> getListConfig(){
        return this.listConfig;
    }
}
