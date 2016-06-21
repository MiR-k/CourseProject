package webdriver.elements;

import org.openqa.selenium.By;

public class RadioButton extends BaseElement {
	
	private String locatorStr;
	private String optionWithValue = new String("[@value='%1$s']");
	
	public RadioButton(final By locator, final String name) {
		super(locator, name);
	}
	
	public RadioButton(final String locatorName, final String name) {
		super(By.xpath(locatorName), name);
		locatorStr = locatorName;
	}

	protected String getElementType() {
		return "RadioButton";
	}

	/**
	 *
	 * @param value String
     */
	public void selectOptionByValue(String value){
		waitAndAssertIsPresent();
		RadioButton optionToSelect = new RadioButton(By.xpath(locatorStr + String.format(optionWithValue, value)), value);
		optionToSelect.click();
	}

	/**
	 *
	 * @param value String
	 *
     */
	public void assertOptionIsSelectedByValue(String value){
		waitAndAssertIsPresent();
		RadioButton optionToSelect = new RadioButton(By.xpath(locatorStr + String.format(optionWithValue, value)), value);
		if (optionToSelect.isChecked()) { 
			info(String.format("Radiobuton with value '%1$s' is selected", value));
		}	else {
			fatal(String.format("Radiobuton with value '%1$s' is not selected!", value));
		}
	}

}
