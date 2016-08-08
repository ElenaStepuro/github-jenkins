package com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	private static final Logger LOG = Logger.getLogger(LoginPage.class);

	private WebDriver driver;
	private Actions actions;

	@FindBy(name = "login")
	private WebElement loginInput;

	@FindBy(name = "passwd")
	private WebElement passwdInput;

	@FindBy(xpath = "//a[contains(text(), 'Почта')]")
	private WebElement btnMail;

	@FindBy(xpath = "//span[contains(text(), 'Войти')]")
	private WebElement btnEntry;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.actions = new Actions(this.driver);
	}

	// Нажатие клавиши для входа в почту
	public LoginPage setBtnMailClick() {
		LOG.info("Start method 'setBtnMailClick'");

		Assert.assertTrue(btnMail.isDisplayed());
		actions.click(btnMail).build().perform();

		LOG.info("Finish method 'setBtnMailClick'");
		return this;
	}

	// Ввод логина
	public LoginPage setLoginInput(String login) {
		LOG.info("Start method 'setLoginInput'");

		Assert.assertTrue(loginInput.isDisplayed());
		loginInput.clear();
		actions.click(loginInput).sendKeys(loginInput, login).build().perform();

		LOG.info("Finish method 'setLoginInput'");
		return this;
	}

	// Ввод пароля
	public LoginPage setPasswdInput(String passwd) {
		LOG.info("Start method 'setPasswdInput'");

		Assert.assertTrue(passwdInput.isDisplayed());
		passwdInput.clear();
		actions.click(passwdInput).sendKeys(passwdInput, passwd).build().perform();

		LOG.info("Finish method 'setPasswdInput'");
		return this;
	}

	// Нажатие клавиши для входа
	public InboxPage setBtnEntryClick() {
		LOG.info("Start method 'setBtnEntryClick'");

		Assert.assertTrue(btnEntry.isDisplayed());
		actions.click(btnEntry).build().perform();

		LOG.info("Finish method 'setBtnEntryClick'");
		return new InboxPage(this.driver);
	}

}
