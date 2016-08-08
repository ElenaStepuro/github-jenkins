package com.epam.elena_stepuro.framework.reporting.run_tests.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestNGReportAppender extends AppenderSkeleton {

	public void close() {

	}

	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		String log = this.layout.format(event);
		log = log.replaceAll("\n", "<br>");
		Reporter.log(log);
	}

}
