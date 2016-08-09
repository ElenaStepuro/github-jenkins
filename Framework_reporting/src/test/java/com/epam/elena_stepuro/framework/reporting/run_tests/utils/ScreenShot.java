package com.epam.elena_stepuro.framework.reporting.run_tests.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	private static final Logger LOG = Logger.getLogger(ScreenShot.class);

	private static final String PATH_TO_REPORT = "target/surefire-reports/html/";
	private static final String SCREENSHOTS_FOLDER = "screenshots";

	public static void make(WebDriver driver) {
		if (driver == null) {
			return;
		}
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER));
			String logMessage = "<a href='" + SCREENSHOTS_FOLDER + "/" + screenshot.getName() + "'>See ScreenShot</a>";
			LOG.info(logMessage);
		} catch (Exception e) {
			LOG.error("There was an error when you try to make screenshot: " + e);
			throw new RuntimeException();
		}
	}

	public static void deleteAll() {
		File directory = new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER);
		LOG.info("Delete all files from folder " + directory.getAbsolutePath());
		File[] files = directory.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (!file.delete()) {
					LOG.info("Cannot delete file " + file);
				}
			}
		}
	}
}
