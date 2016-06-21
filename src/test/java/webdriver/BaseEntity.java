package webdriver;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;

import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.ITestContext;

import webdriver.Browser.Browsers;
import webdriver.elements.Label;


/**
 * BaseEntity
 */
public abstract class BaseEntity {

	protected static int stepNumber = 1;
	protected static Logger logger = Logger.getInstance();
	protected static Browser browser = Browser.getInstance();
	protected static boolean isLogged = false;
	protected static int screenIndex = 0;
	protected ITestContext context;


	/**
	 * Get locale
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	protected static String getLoc(final String key) {
		return Logger.getLoc(key);
	}

	// ==============================================================================================
	// Methods for logging

	/**
	 * Format message.
	 * 
	 * @param message
	 *            message
	 * @return null
	 */
	protected abstract String formatLogMsg(String message);

	

	/**
	 * Informative message.
	 * 
	 * @param message
	 *            Message
	 */
	protected void info(final String message) {
		logger.info(formatLogMsg(message));
	}

	/**
	 * Warning.
	 * 
	 * @param message
	 *            Message
	 */
	protected void warn(final String message) {
		logger.warn(formatLogMsg(message));
	}

	/**
	 * Error message without stopping the test.
	 * 
	 * @param message
	 *            Message
	 */
	protected void error(final String message) {
		logger.error(formatLogMsg(message));
	}

	/**
	 * Fatal error message.
	 * 
	 * @param message
	 *            Message
	 */
	protected void fatal(final String message) {
		logger.fatal(formatLogMsg(message));
		assertTrue(formatLogMsg(message), false);
	}

	/**
	 * Logging a step number.
	 * 
	 * @param step
	 *            - step number
	 */
	public static void logStep(final int step) {
		logger.step(step);
	}

	/**
	 * Logging a several steps in a one action
	 * 
	 * @param fromStep
	 *            - the first step number to be logged
	 * @param toStep
	 *            - the last step number to be logged
	 */
	public void logStep(final int fromStep, final int toStep) {
		logger.step(fromStep, toStep);
	}

	// ==============================================================================================
	// Asserts

	/**
	 * Universal method
	 * 
	 * @param isTrue
	 *            Condition
	 * @param passMsg
	 *            Positive message
	 * @param failMsg
	 *            Negative message
	 */
	public void doAssert(final Boolean isTrue, final String passMsg,
			final String failMsg) {
		if (isTrue) {
			info(passMsg);
		} else {
			fatal(failMsg);
		}
	}

	/**
	 * Assert Objects are Equal
	 * 
	 * @param expected
	 *            Expected Value
	 * @param actual
	 *            Actual Value
	 */
	public void assertEquals(final Object expected, final Object actual) {
		if (!expected.equals(actual)) {
			fatal("Expected value: '" + expected + "', but was: '" + actual
					+ "'");
		}
	}

	

	/**
	 * Assert Objects are Equal
	 * 
	 * @param message
	 *            Fail Message
	 * @param expected
	 *            Expected Value
	 * @param actual
	 *            Actual Value
	 */
	public void assertEquals(final String message, final Object expected,
			final Object actual) {
		if (!expected.equals(actual)) {
			fatal(message);
		}
	}

	

	

	/**
	 * killing process by Image name
	 */
	public void checkAndKill() {
		logger.info("killing processes");
		try {
			String line;
			Process p = Runtime.getRuntime().exec(
					String.format("taskkill /IM %1$s.exe /F",
							Browser.currentBrowser.toString()));
			BufferedReader input = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			while (((line = input.readLine()) != null)) {
				logger.info(line);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Only for IE
	 */
	public void acceptUnsignedSertificate() {
		if (Browser.currentBrowser == Browsers.IEXPLORE) {
			browser.navigate("javascript:document.getElementById('overridelink').click()");
		}
	}


	/**
	 * Logging steps
	 */
	protected void LogStep() {
		logStep(stepNumber++);
	}

	/**
	 * Logging steps with info
	 */
	protected void LogStep(final String info) {
		logStep(stepNumber++);
		logger.info(String.format("----==[ %1$s ]==----", info));
	}

	public void createScreenShot(Class<? extends BaseEntity> name) {
		Date date = new Date();
		DateFormat dF = new SimpleDateFormat("HH-mm-ss");
		String fileName = name.getSimpleName() +"_" +dF.format(date);
		try {
			File screen = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(String.format("surefire-reports/html/Screenshots/%1$s.png", fileName)));
		} catch (Exception e) {
			warn("Create screenshot failed");
		}
		String formattedName = String.format("<a href='Screenshots/%1$s.png'>Screenshots</a>", fileName);
		logger.info(formattedName);
	}

	/**
	 * Method create name as login+start_time_test
	 * @param login String
	 *              login name
	 * @return String
	 *          New Name as login and test start time
	 */
	public String createNewName(String login){
		Date date = new Date();
		DateFormat dF = new SimpleDateFormat("HH:mm:ss");
		String sublogin = login.substring(0,login.indexOf("@"));
		String newName = String.format("%s-%s", sublogin, dF.format(date));
		info("Create "+newName);
		return newName;
	}

	public List<WebElement> findElements(By locator){
		List<WebElement> list= browser.getDriver().findElements(locator);
		return list;
	}

	/**
	 * Method enter Frame by Name
	 * @param nameFrame String
	 *                  Frame name
     */
	public void toFrame(String nameFrame){
		browser.getDriver().switchTo().frame(nameFrame);
	}

	public void leaveFrame(){
		browser.getDriver().switchTo().defaultContent();
	}

	public String firstUpperCase(String word){
		if(word == null || word.isEmpty()) return "";//или return word;
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}

	/**
	 * method check the occurrence of the string returns true if there is
	 * @param queryString String
	 *                     query of string
	 * @param partResult String
	 *                   part of result search
	 * @return boolean
	 *          whether or not the line
	 */
	public boolean verifEntry (String queryString, String partResult){
		String queryPart[] = queryString.split(" ");
		String tempString;
		int matches =0;
		for(int i = 0; i < queryPart.length; i++) {
			if ((queryPart[i].length() < 1 && i != 0)) {
				tempString = queryPart[i] + queryPart[i-1];
			} else {
				tempString = queryPart[i];
			}
			if (partResult.contains(tempString.substring(0,tempString.length()))){
				matches++;
			}
		}
		return (matches > 0);
	}

	/**
	 * Method Accept pop-up warning
	 */
	public void alertAccept(){
		browser.getDriver().switchTo().alert().accept();
		browser.getDriver().switchTo().defaultContent();
	}

	/**
	 * Method checks the goods ID
	 *
	 * @param listParts List
	 *                  List element from Configurator or Catalog
	 */
	public void checkFor(List<String> listParts,By xPath){
		List<WebElement> listBasket = findElements(xPath);
		Assert.assertEquals(listBasket.size(),listParts.size());
		for(WebElement item:listBasket){
			for (String part:listParts){
				if (part.contains(item.getText())){
					Assert.assertTrue(part.contains(item.getText()));
					info(String.format("Correct add to basket from ID:%s",item.getText()));
				}
			}
		}
	}

	/**
	 * Method scroll down to elemet top (for low resolution monitor)
	 * @param xPath BY
	 *              locator element
     */
	public void scrollDown(By xPath) {
		WebElement element = browser.getDriver().findElement(xPath);
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
	}
}

