package com.epam.elena_stepuro.framework.reporting.framework.exceptions;

import java.util.NoSuchElementException;

public class FirefoxProfileNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 5484541856468465484L;

	public FirefoxProfileNotFoundException(String message) {
		super(message);
	}

}
