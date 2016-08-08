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

public class DraftPage {

	private static final Logger LOG = Logger.getLogger(DraftPage.class);

	@FindBy(xpath = "//div/span/span/span/span/span")
	private WebElement checkTitle;

	@FindBy(xpath = "//a/div/span[2]/span[2]/span/span[1]/span")
	private WebElement checkTheme;

	@FindBy(xpath = "//span[contains(text(), 'Сохранить и перейти')]")
	private WebElement saveAndOpen;

	@FindBy(xpath = "//div/a[2]/span[contains(text(), 'Отправленные')]")
	private WebElement btnSendPackage;

	@FindBy(xpath = "(//span[@class='mail-ui-Icon'])[1]")
	private WebElement checkMark;

	@FindBy(xpath = "//div[@title='Удалить (Delete)']")
	private WebElement delete;

	private WebDriver driver;

	private Actions actions;

	public DraftPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.actions = new Actions(this.driver);
	}

	// Нажатие кнопки "Сохранить и перейти"
	public DraftPage saveAndOpen() {
		LOG.info("Start method 'saveAndOpen'");

		Assert.assertTrue(saveAndOpen.isDisplayed());
		actions.click(saveAndOpen).build().perform();

		LOG.info("Finish method 'saveAndOpen'");
		return new DraftPage(this.driver);
	}

	// Проверяет наличие письма в черновиках
	public Boolean letterSaved() {
		LOG.info("Start method 'letterSaved'");

		Boolean result = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {

					public Boolean apply(WebDriver driver) {
						return checkTheme.isDisplayed();
					}
				});

		LOG.info("Finish method 'letterSaved'");
		return result;
	}

	// Открытие последнего письма в черновиках
	public WriteLetterPage openLastLetter(String subjText) {
		LOG.info("Start method 'openLastLetter'");

		Assert.assertEquals(checkTitle.getAttribute("title"), subjText);
		actions.click(checkTitle).build().perform();

		LOG.info("Finish method 'openLastLetter'");
		return new WriteLetterPage(this.driver);
	}

	// Нажатие кнопки "Отправленные"
	public SendPage btnSendPackageClick() {
		LOG.info("Start method 'btnSendPackageClick'");

		Assert.assertTrue(btnSendPackage.isDisplayed());
		actions.click(btnSendPackage).build().perform();

		LOG.info("Finish method 'btnSendPackageClick'");
		return new SendPage(this.driver);
	}

	// Отмечает галочкой все сообщения
	public DraftPage checkAllDrafts() {
		LOG.info("Start method 'checkAllDrafts'");

		Assert.assertTrue(checkMark.isEnabled());
		actions.click(checkMark).build().perform();

		LOG.info("Finish method 'checkAllDrafts'");
		return this;
	}

	// Удаляет все сообщения
	public DraftPage deleteMarked() {
		LOG.info("Start method 'deleteMarked'");

		if (delete.isDisplayed())
			actions.click(delete).build().perform();

		LOG.info("Finish method 'deleteMarked'");
		return this;
	}
}
