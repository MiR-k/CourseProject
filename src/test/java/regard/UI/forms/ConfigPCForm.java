package regard.UI.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;
import webdriver.elements.Label;
import java.util.ArrayList;
import java.util.List;

public class ConfigPCForm extends BaseForm {

    private String cmbBox = "//span[contains(text(),\"%s\")] /following-sibling::select";
    private Button btnCheckout = new Button(By.xpath("//input[@type='submit']"),"Button print configuration");
    private Label lblPrice = new Label(By.xpath("//td[@width='80']/span"),"Label price");
    private Label totalCost = new Label(By.xpath("//b //span"),"Total Cost");
    private static String brandCPU = "//h3[contains(text(),\"%s\")]";
    private static String titleBrand = "//h2[contains(text(),\"%s\")]";

    private List<String> listPart = new ArrayList<String>();
    private List<String> listConfig = new ArrayList<String>();

    public ConfigPCForm(){
        super(By.xpath("//h1[contains(text(),\"Конфигуратор\")]"),"Configurator PC Form");
    }

    /**
     * Method choose manufacturer PC Configurator
     * @param nameBrand String
     *                  name manufacturer CPU
     */
    public void selectCPU(String nameBrand){
        if(nameBrand.toLowerCase().contains("amd")){
            nameBrand = "AMD";
        }
        if(nameBrand.toLowerCase().contains("intel")){
            nameBrand = "Intel";
        }
        Label lblBrandCPU = new Label(By.xpath(String.format(brandCPU, nameBrand)),String.format("Choose %s",nameBrand));
        lblBrandCPU.waitAndClick();
        Label lblBrand = new Label(By.xpath(String.format(titleBrand, nameBrand)),String.format("The base %s",nameBrand));
        lblBrand.waitAndAssertIsPresent();
    }

    /**
     * Method add item in List
     * @param part String
     *             name item
     */
    public void addToConfig(String part){
        listPart.add(part);
    }

    /**
     * Method add to configurator item, create screenshot and checkout to basket
     *
     */
    public void configAndCheckOut(){
        for (String part:listPart) {
            configurator(part);
        }
        createScreenShot(this.getClass());
        btnCheckout.waitAndClick();
    }

    /**
     * Add one item from List to configurator
     * @param part String
     *             name item
     */
    public void configurator(String part){
        Label list = new Label(By.xpath("//table[@class='cfg_table']//tr//select"));
        ComboBox temp;
       int i =1;
         do {
            temp = new ComboBox(By.xpath(String.format("//table[@class='cfg_table']//tr[%s]//select", i)),
                    String.format("ComboBox %s", i));
            if (temp.isPresent()) {
                if (verifEntry(part,temp.getText())){
                    temp.selectByPartialText(part.substring(part.lastIndexOf(" ")).trim());
                    listConfig.add(temp.getSelectedLabel());
                    info(String.format("Add to config:%s",temp.getSelectedLabel()));
                break;
                }
            }
             i++;
        } while(list.isPresent());
    }

    /**
     * method return list item add to configurator
     * @return List String
     */
    public List<String> getListConfig(){
        return this.listConfig;
    }

}
