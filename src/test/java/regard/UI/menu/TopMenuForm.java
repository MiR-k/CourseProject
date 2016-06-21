package regard.UI.menu;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class TopMenuForm extends BaseForm {

    private String lblMenu = "//ul[@id='main-menu'] //a[contains(text(),\"%s\")]";
    /**
     * Constructor TopMenuForm
     */
    public TopMenuForm(){
        super(By.id("main-menu"), "Top menu");
    }

    public void navigateMenu(String name){
        Label lblTopMenu = new Label(By.xpath(String.format(lblMenu,name)), name);
        lblTopMenu.waitAndClick();
    }

}
