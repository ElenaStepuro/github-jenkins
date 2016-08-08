package com.epam.elena_stepuro.framework.reporting.framework.config;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TestNgConfig {
	
	private static List<String> includeGroups = new ArrayList();
	
	private static List<String> excludeGroups = new ArrayList();

	private static List<String> testngConfigs = new ArrayList();

	public static List<String> getIncludeGroups() {
		return includeGroups;
	}

	public static void setIncludeGroups(List<String> includeGroups) {
		TestNgConfig.includeGroups = includeGroups;
	}

	public static List<String> getExcludeGroups() {
		return excludeGroups;
	}

	public static void setExcludeGroups(List<String> excludeGroups) {
		TestNgConfig.excludeGroups = excludeGroups;
	}

	public static List<String> getTestngConfigs() {
		return testngConfigs;
	}

	public static void addTestngConfig(String testngConfig){
		testngConfigs.add(testngConfig);
	}
}
