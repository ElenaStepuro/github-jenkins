package com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class WriteLetterPage {

	private static final Logger LOG = Logger.getLogger(WriteLetterPage.class);

	@FindBy(xpath = "//input[@name='to']/..")
	private WebElement whomInput;

	@FindBy(xpath = "//input[@name = 'subj']")
	private WebElement subjectInput;

	@FindBy(xpath = "//div[@role='textbox']")
	private WebElement form;

	@FindBy(xpath = "//span[contains(text(), 'Черновики')]")
	private WebElement draftOpen;

	@FindBy(xpath = "//div[@role='textbox']/div")
	private WebElement checkForm;

	@FindBy(xpath = "//div/span[3]")
	private WebElement checkTheme;

	@FindBy(xpath = "//div/button[@title='Отправить письмо (Ctrl + Enter)']")
	private WebElement btnSend;

	private WebDriver driver;

	private Actions actions;

	public WriteLetterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.actions = new Actions(this.driver);
	}

	// Проверяет открытие формы
	public Boolean formOpened() {
		LOG.info("Start method 'formOpened'");

		Boolean result = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {

					public Boolean apply(WebDriver driver) {
						return form.isDisplayed();
					}
				});

		LOG.info("Finish method 'formOpened'");
		return result;
	}

	// Заполнение получателя письма
	public WriteLetterPage whomInput(String whomText) {
		LOG.info("Start method 'whomInput'");

		Assert.assertTrue(whomInput.isEnabled());
		actions.click(whomInput).sendKeys(whomInput, whomText).build().perform();

		LOG.info("Finish method 'whomInput'");
		return this;
	}

	// Заполнение темы письма
	public WriteLetterPage sbjInput(String subjText) {
		LOG.info("Start method 'sbjInput'");

		Assert.assertTrue(subjectInput.isEnabled());
		actions.click(subjectInput).sendKeys(subjectInput, subjText).build().perform();

		LOG.info("Finish method 'sbjInput'");
		return this;
	}

	// Заполнение тела письма
	public WriteLetterPage formInput(String formText) {
		LOG.info("Start method 'formInput'");

		actions.click(form).sendKeys(form, formText).build().perform();

		LOG.info("Finish method 'formInput'");
		return this;
	}

	// Нажатие кнопки "Черновики"
	public DraftPage draftOpen() {
		LOG.info("Start method 'draftOpen'");

		Assert.assertTrue(draftOpen.isDisplayed());
		actions.click(draftOpen).build().perform();

		LOG.info("Finish method 'draftOpen'");
		return new DraftPage(this.driver);
	}

	// Проверяет заполненную тему письма
	public WriteLetterPage checkSubj(String subjText) {
		LOG.info("Start method 'checkSubj'");

		Assert.assertEquals(subjectInput.getAttribute("value"), subjText);
		LOG.info("The theme of letter is right!");

		LOG.info("Finish method 'checkSubj'");
		return this;
	}

	// Проверяет заполненное тело письма
	public WriteLetterPage checkFormText(String formText) {
		LOG.info("Start method 'checkFormText'");

		Assert.assertEquals(checkForm.getText(), formText);
		LOG.info("The body of letter is right!!");

		LOG.info("Finish method 'checkFormText'");
		return this;
	}

	// Нажатие кнопки "Отправить"
	public SendPage btnSendClick() {
		LOG.info("Start method 'btnSendClick'");

		Assert.assertTrue(btnSend.isDisplayed());
		actions.click(btnSend).build().perform();

		LOG.info("Finish method 'btnSendClick'");
		return new SendPage(this.driver);
	}

}
