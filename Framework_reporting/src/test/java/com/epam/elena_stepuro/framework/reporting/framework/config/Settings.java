package com.epam.elena_stepuro.framework.reporting.framework.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
//import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Settings {

	@Option(name = "--testng", usage = "Set path to testng.xml file", required = true)
	public String pathToTestng;

	@Option(name = "--login", usage = "Set connection user login", required = true)
	public String login;

	@Option(name = "--password", usage = "Set connection user password")
	public String passwd;

	@Option(name = "--url", usage = "Set connection url")
	public String url;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	// public static void main(String[] args) {
	// Settings settings = new Settings();
	// CmdLineParser parser = new CmdLineParser(args);
	// try {
	// parser.parseArgument(args);
	// System.out.println("Settings: " + settings);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
