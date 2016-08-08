package com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SendPage {

	private static final Logger LOG = Logger.getLogger(SendPage.class);

	@FindBy(xpath = "//div/span[1]/span/span[contains(text(),'')]")
	private WebElement ckeckLetter;

	@FindBy(xpath = "//span[contains(text(), 'Черновики')]")
	private WebElement draftOpen;

	@FindBy(xpath = "(//span[@class='mail-ui-Icon'])[1]")
	private WebElement checkMark;

	@FindBy(xpath = "//div[@title='Удалить (Delete)']")
	private WebElement delete;

	private WebDriver driver;

	private Actions actions;

	public SendPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.actions = new Actions(this.driver);
	}

	// Проверка письма в папке "Отправленные"
	public SendPage checkLetter(String letterText) {
		LOG.info("Start method 'checkLetter'");

		actions.click(ckeckLetter).build().perform();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='mail-Message-Body-Content']/div")).getText(),
				letterText);
		LOG.info("Letter is in the package.");

		LOG.info("Finish method 'checkLetter'");
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

	// Выделение всех писем папки
	public SendPage markLetters() {
		LOG.info("Start method 'markLetters'");

		Assert.assertTrue(checkMark.isEnabled());
		actions.click(checkMark).build().perform();

		LOG.info("Finish method 'markLetters'");
		return this;
	}

	// Удаление всех писем папки
	public SendPage deleteMarkedLetters() {
		LOG.info("Start method 'deleteMarkedLetters'");

		Assert.assertTrue(delete.isDisplayed());
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", delete);

		LOG.info("Finish method 'deleteMarkedLetters'");
		return this;
	}
}
