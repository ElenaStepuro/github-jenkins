package com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class InboxPage {

	private static final Logger LOG = Logger.getLogger(InboxPage.class);

	@FindBy(xpath = "//div[contains(text(), 'stepuro.e@yandex.ru')]")
	private WebElement yandexLogin;

	@FindBy(xpath = "//span[contains(text(), 'Написать')]")
	private WebElement btnCreate;

	private WebDriver driver;

	private Actions actions;

	public InboxPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.actions = new Actions(this.driver);
	}

	// Проверяет вход в почту
	public Boolean onMailPage() {
		LOG.info("Start method 'onMailPage'");

		Boolean result = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {

					public Boolean apply(WebDriver driver) {
						return yandexLogin.isDisplayed();
					}
				});

		LOG.info("Finish method 'onMailPage'");
		return result;
	}

	// Нажатие кнопки "Написать"
	public WriteLetterPage btnWriteClick() {
		LOG.info("Start method 'btnWriteClick'");

		Assert.assertTrue(btnCreate.isDisplayed());
		actions.click(btnCreate).build().perform();

		LOG.info("Finish method 'btnWriteClick'");
		return new WriteLetterPage(this.driver);
	}
}
