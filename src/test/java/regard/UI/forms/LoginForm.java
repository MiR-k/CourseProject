package regard.UI.forms;


import org.junit.Assert;
import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class LoginForm extends BaseForm{

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private TextBox txbLogin = new TextBox(By.name("login"), "Login");
    private TextBox	txbPassword = new TextBox(By.name("pass"), "Password");
    private Button btnSend = new Button(By.name("button-login"), "Login button");
    private static String locatorNavigate = "//div[@id='login-form'] //a[contains(text(),\"%s\")]";

    public LoginForm(){
        super(By.id("login-form"),"Login Form");
    }

    /**
     * Login Method
     * @param login String
     *              Login to Personal Area
     * @param password String
     *              Password to Personal Area
     */
    public void login(String login, String password) {
        assert (login.matches(EMAIL_PATTERN));
        txbLogin.type(login);
        txbPassword.type(password);
        btnSend.click();
    }

    /**
     * Navigate Personal Area
     */
    public void lfNavigate(String nameTo){
        Label lblBasket = new Label(By.xpath(String.format(locatorNavigate,nameTo)),String.format("Label %s",nameTo));
        lblBasket.click();
    }

    /**
     * Method Exit and enter to Personal Area
     * @param login String
     *              Login to Personal Area
     * @param password String
     *              Password to Personal Area
     */
    public void reLogin(String login, String password){
        Button lblPrivateArea = new Button(By.xpath("//span[@id='username']/.. //button"),"Label Personal Area");
        lblPrivateArea.isPresent();
        lblPrivateArea.click();
        login(login, password);
    }

    /**
     * Method compares the display name and generated
     * @param newName String
     *              new generate Name
     */
    public void checkVisibleName(String newName){
        Label lblName = new Label(By.xpath("//div[@id='login-form'] //span[@id='username']"));
        org.testng.Assert.assertEquals(newName,lblName.getText());
        String message = String.format("Correct: new name %s equals present's %s\"",newName,lblName.getText());
        info(message);
    }

}
