package regard.UI.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class PersonalAreaForm extends BaseForm{

    private static String lblInsert = "//div[contains(text(),\"%s\")]";
    private static String lblTextBox = "//td[contains(text(),\"%s\")]/.. //input";
    private Button btnSave = new Button(By.xpath("//button[contains(text(),\"Сохранить\")]"),"Save button");
    private Label lblMessage = new Label(By.className("alarm"),"Message");

    private String newName;

    public PersonalAreaForm(){
        super(By.xpath("//h1[contains(text(),\"Личный кабинет\")]"), "Personal Area");
    }

    /**
     * Method check edit information
     *
     * @param loginName String
     *                  login name
     * @param nameParameter String
     *                      a parameter change's
     */
    public void checkInformation(String loginName, String nameParameter){
        TextBox txtParametr = new TextBox(By.xpath(String.format(lblTextBox,nameParameter)),nameParameter);
        toFrame("iframe-info");
        Assert.assertEquals(loginName,txtParametr.getValue());
        String message = String.format("Correct: input login %s equals in personal information %s\"",loginName,txtParametr.getValue());
        info(message);
        leaveFrame();
    }

    /**
     * method navigate Personal area byName
     * @param nameInsert String
     *                   name subsection
     */
    public void navigatePersonalArea(String nameInsert){
        Label lblNavigate = new Label(By.xpath(String.format(lblInsert,nameInsert)),nameInsert);
        lblNavigate.waitAndClick();
    }

    /**
     *method navigate Personal area byName
     * @param nameInsert String
     *                   name subsection
     * @param nameEnglish String
     *                    name english for printing
     */
    public void navigatePersonalArea(String nameInsert, String nameEnglish){
        Label lblNavigate = new Label(By.xpath(String.format(lblInsert,nameInsert)),nameEnglish);
        lblNavigate.waitAndClick();
    }

    /**
     * Method edit and save parameter
     * @param login String
     *              input value for create new name
     * @param nameParametr String
     *                     name label for edit
     */
    public void editInformation(String login, String nameParametr){
        toFrame("iframe-info");
        TextBox txtParametr = new TextBox(By.xpath(String.format(lblTextBox,nameParametr)),nameParametr);
        this.newName = createNewName(login);
        txtParametr.setText(newName);
        btnSave.click();
        lblMessage.waitAndAssertIsPresent();
        leaveFrame();
    }

    public String getNewName(){
        return this.newName;
    }
}

