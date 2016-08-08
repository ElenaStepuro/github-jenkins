package com.epam.elena_stepuro.framework.reporting.run_tests.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.elena_stepuro.framework.reporting.framework.ui.driver.Driver;
import com.epam.elena_stepuro.framework.reporting.run_tests.test.YandexTest;

public class ScreenshotListener implements ITestListener {

	private static final Logger LOG = Logger.getLogger(YandexTest.class);

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
		LOG.warn("Start 'onTestFailure'");
		screenshot(result);
		LOG.warn("Finish 'onTestFailure'");
	}

	private void screenshot(ITestResult result) {
		WebDriver driver = Driver.getDriver();
		if (driver != null)
			ScreenShot.make(driver);
	}

	public void onTestSkipped(ITestResult result) {
		LOG.warn("Start 'onTestSkipped'");
		screenshot(result);
		LOG.warn("Finish 'onTestSkipped'");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
