package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Combobox (drop-down list) class
 */
public class ComboBox extends BaseElement {

	public ComboBox(final By locator, final String name) {
		super(locator, name);
	}

	protected String getElementType() {
		return "ComboBox";
	}

	/**
	 *
	 */
	public String getSelectedLabel() {
		assertIsPresent();
		return new Select(element).getFirstSelectedOption().getText();
	}

	public void selectByPartialText(String text) {
		waitAndAssertIsPresent();
		info(String.format("Selecting value with text '%1$s'", text));
		List<WebElement> options = element.findElements(By.xpath(".//option[contains(.,'" + text + "')]"));
		boolean matched = false;
		    for (WebElement option : options) {
		      option.click();
		      matched = true;
		    }
		 if (!matched) {
		    throw new NoSuchElementException("Cannot locate element containing text: " + text);
		    }
	}

	public String getSelectedText(){
		waitAndAssertIsPresent();
		return (new Select(element)).getFirstSelectedOption().getText();
	}
	
	public void checkSelectedText(String value){
		waitAndAssertIsPresent();
		String actualValue = getSelectedText();
		if (!value.equals(actualValue)) {
			error(String.format("Wrong value '%1$s'. Expected: '%2$s', Actual: '%3$s'", getName(), value, actualValue));
		}
	}

	public String getName() {
		return name;
	}
}
