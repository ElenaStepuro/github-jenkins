package com.epam.elena_stepuro.framework.reporting.framework.ui.driver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.epam.elena_stepuro.framework.reporting.framework.exceptions.UnknownDriverTypeException;

public class Driver {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";

	private static WebDriverType defaultDriverType = WebDriverType.FIREFOX;

	private static HashMap<String, org.openqa.selenium.WebDriver> instance;

	static {
		instance = new HashMap<String, WebDriver>();
	}

	public static WebDriver getWebDriverInstance(String name, WebDriverType type) {
		if (!instance.containsKey(name)) {
			switch (type) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case IE:
				driver = new InternetExplorerDriver();
				break;
			default:
				throw new UnknownDriverTypeException("Unknown driver specified: " + type.getDriverName());
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			instance.put(name, driver);
		} else {
			driver = instance.get(name);
		}
		return driver;
	}

	public static WebDriver getWebDriverInstance(String name) {
		return getWebDriverInstance(name, defaultDriverType);
	}

	public static WebDriver getWebDriverInstance() {
		return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
	}

	public static void setWebDriverInstance(WebDriverType type) {
		defaultDriverType = type;
	}

	public static Boolean isTextPresent(WebDriver driver, String text) {
		return driver.getPageSource().contains(text);
	}

}
