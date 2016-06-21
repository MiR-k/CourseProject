package webdriver;

import org.apache.tools.ant.types.resources.comparators.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * An abstract class that describes the basic test application contains
 * methods for logging and field test settings (options)
 */
abstract public class BaseTest extends BaseEntity{


	private static boolean warn = false;

	public static void setWarn(){
		warn = true;
	}

	public static boolean hasWarn(){
		return warn;
	}

	/**
	 * Method which run before tests
	 */
	@BeforeMethod
	public void methodBefore() throws Throwable {
		try {
			warn = false;
			browser = Browser.getInstance();
			browser.setError(false);
			logger.logTestName(this.getClass().getName());
			browser.navigate(Browser.getBaseUrl());
			browser.windowMaximise();
		} catch (Throwable e) {
			logger.warn("");
			logger.warnRed(getLoc("loc.test.failed"));
			logger.warn("");
			logger.fatal(e.getMessage());
		}
	}

	/**
	 * Method which run after tests
	 */
	@AfterMethod
	public void methodAfter() throws Exception {
		try {
			createScreenShot(this.getClass());
			logger.logTestEnd(this.getClass().getName());
			System.runFinalization();
		} catch (Exception e) {
			logger.fatal(e.getMessage());
		}
		browser.exit();
	}

	/**
	 * Format logging
	 * @param message Message
	 * @return Message
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}



}
