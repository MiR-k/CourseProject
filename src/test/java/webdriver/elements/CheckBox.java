package webdriver.elements;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;

/**
 * Class describes checkbox
 */
public class CheckBox extends BaseElement {

    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return "CheckBox";
    }

    /**
     * Assert that checkbox is present
     * Test fails if not
     * @param expectedState
     *				Expected checkbox state
     */
    public void assertState(boolean expectedState) {
        assertIsPresent();
        info(String.format("Checking that value is '%1$s'", expectedState));
        assertEquals(formatLogMsg("Wrong value of a checkbox!"), expectedState,
                isChecked());
    }

    /**
     * Set value TRUE for checkbox
     * Assert that checkbox is checked
     * Test fails if not
     */
    public void check() {
        check(true);
        assertEquals(formatLogMsg("Checkbox wasn't checked!"),false,isChecked());
    }

    /**
     * Set value selected
     *
     * @param state
     *            value (TRUE/FALSE)
     */
    public void check(boolean state) {
        assertIsPresent();
        info(String.format("Setting value '%1$s'", state));
        if ((state ^ element.isSelected())) {
            element.click();
        }
    }

    /**
     * Set random value
     *
     * @return value selected
     */
    public boolean checkRandom() {
        boolean state = new Random().nextBoolean();
        check(state);
        return state;
    }

    /**
     * Return checkbox value (TRUE/FALSE)
     */
    public boolean isChecked() {
        assertIsPresent();
        return element.isSelected();
    }

    /**
     * Change checkbox value to opposite
     */
    public void toggle() {
        check(!isChecked());
    }

    /**
     * Set value FALSE for checkbox
     */
    public void uncheck() {
        check(false);
    }
}
