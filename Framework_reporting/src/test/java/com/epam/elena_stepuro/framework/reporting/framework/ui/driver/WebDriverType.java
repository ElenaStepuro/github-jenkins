package com.epam.elena_stepuro.framework.reporting.framework.ui.driver;

public enum WebDriverType {

	FIREFOX("firefox"), IE("enternet explorer");

	private String driverName;

	private WebDriverType(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverName() {
		return driverName;
	}

}
