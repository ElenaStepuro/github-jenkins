package com.epam.elena_stepuro.framework.reporting.framework.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import com.epam.elena_stepuro.framework.reporting.framework.config.Settings;
import com.epam.elena_stepuro.framework.reporting.framework.config.TestNgConfig;
import com.epam.elena_stepuro.framework.reporting.framework.ui.driver.Driver;

public class Runner {

	protected TestNG testNg = new TestNG();

	@SuppressWarnings("rawtypes")
	protected List<Class> listener;

	public static void main(String[] args) {
		try {
			new Runner(args).run();
		} catch (Exception e) {
			Reporter.log(e.getMessage(), 2, true);
		} finally {
			Driver.getWebDriverInstance().close();
		}
	}

	public Runner(String[] args) {
		Settings settings = new Settings();
		CmdLineParser parser = new CmdLineParser(settings);

		try {
			parser.parseArgument(args);
			TestNgConfig.addTestngConfig(settings.pathToTestng);
		} catch (CmdLineException e) {
			System.err.println("e = " + e.toString());
			parser.printUsage(System.out);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Runner(String[] args, Class listenerClass) {
		this.listener = new ArrayList();
		this.listener.add(listenerClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() throws ParserConfigurationException, IOException {
		try {
			List includeGroups = TestNgConfig.getIncludeGroups();
			List excludeGroups = TestNgConfig.getExcludeGroups();

			for (String suite : TestNgConfig.getTestngConfigs()) {
				for (XmlSuite xmlSuite : new Parser(suite).parseToList()) {
					for (XmlTest test : xmlSuite.getTests()) {
						test.getIncludedGroups().addAll(includeGroups);
						test.getExcludedGroups().addAll(excludeGroups);
					}
					this.testNg.setCommandLineSuite(xmlSuite);
				}
			}
			this.testNg.run();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
