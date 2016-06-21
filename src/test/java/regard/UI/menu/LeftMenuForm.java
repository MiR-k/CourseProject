package regard.UI.menu;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

public class LeftMenuForm extends BaseForm{

    private static String locatorNavigate = "//div[@id='lmenu']//a[contains(text(),\"%s\")]";
    private static String locatorCategory = "//span[contains(text(),\"%s\")]";
    private String locatorAll  = "/..//a[contains(@class,\"red\")]";


    public LeftMenuForm(){
        super(By.id("lmenu"), "Left Menu");
    }

    /**
     * Method Select category
     * @param name String
     *
     */
    public void selectCategory(String name) {
        Label lblCategory = new Label(By.xpath(String.format(locatorCategory,name)),name);
        lblCategory.click();
    }

    /**
     * Method navigate to the name
     * @param nameCategory String
     *                     Name Category
     */
    public void leftNavigate(String nameCategory){
        Label lblBrand = new Label(By.xpath(String.format(locatorNavigate,nameCategory)), nameCategory);
        lblBrand.waitAndClick();

    }

    /**
     * Method navigate to the name and mark all goods
     * @param nameCategory String
     *                     Name Category
     */
    public void allSubItem(String nameCategory){
        Label lblBrand = new Label(By.xpath(String.format(locatorNavigate,nameCategory)), nameCategory);
        lblBrand.waitAndClick();
        Label lblSubMenu = new Label(By.xpath(String.format(locatorNavigate+locatorAll,nameCategory)), nameCategory);
        lblSubMenu.waitAndClick();
    }
}
