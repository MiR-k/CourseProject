package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Класс, описывающий пункт меню
 */
public class MenuItem extends BaseElement {

	protected static final String DELIMITER = " -> "; // разделитель пунктов
														// меню (используется
														// для логирования)
	
	public MenuItem(final By locator, final String[] names) {
		super(locator, getName(names));
	}

	protected String getElementType() {
		return "Menu Item";
	}

	/**
	 * Формирование полного названия пункта меню (используется для логирования)
	 * 
	 * @param names
	 *            Массив имен отдельных пунктов сложного меню
	 */
	protected static String getName(final String[] names) {
		StringBuffer result = new StringBuffer(names[0]);
		for (int i = 1; i < names.length; i++) {
			result.append(DELIMITER);
			result.append(names[i]);
		}
		return result.toString();
	}
	
	
}
