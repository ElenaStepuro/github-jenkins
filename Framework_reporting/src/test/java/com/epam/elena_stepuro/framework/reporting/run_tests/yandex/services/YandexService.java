package com.epam.elena_stepuro.framework.reporting.run_tests.yandex.services;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.epam.elena_stepuro.framework.reporting.framework.ui.driver.Driver;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.business_ob.Account;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.business_ob.Letter;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens.DraftPage;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens.InboxPage;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens.LoginPage;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens.SendPage;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.screens.WriteLetterPage;

public class YandexService {

	private static final Logger LOG = Logger.getLogger(YandexService.class);

	private LoginPage loginPage = PageFactory.initElements(Driver.getWebDriverInstance(), LoginPage.class);
	private InboxPage inboxPage = PageFactory.initElements(Driver.getWebDriverInstance(), InboxPage.class);
	private WriteLetterPage writeLetterPage = PageFactory.initElements(Driver.getWebDriverInstance(),
			WriteLetterPage.class);
	private DraftPage draftPage = PageFactory.initElements(Driver.getWebDriverInstance(), DraftPage.class);
	private SendPage sendPage = PageFactory.initElements(Driver.getWebDriverInstance(), SendPage.class);

	// Авторизоваться в системе пользователем.
	// Отображается на экране почта пользователя.
	public void logonYandex(Account account) {
		LOG.info("Start method 'logonYandex'");

		loginPage.setBtnMailClick().setLoginInput(account.getLogin()).setPasswdInput(account.getPasswd())
				.setBtnEntryClick();

		LOG.info("Finish method 'logonYandex'");
	}

	// Нажать кнопку "Написать". Проверить, что открылась форма письма.
	// Заполнить поля "Кому", "Тема" и "Тело". Сохранить письмо как черновик.
	// Проверить, что письмо сохранилось, как черновик.
	public void writeSaveLetter(Letter letter) throws InterruptedException {
		LOG.info("Start method 'writeSaveLetter'");

		Thread.sleep(5000);

		inboxPage.btnWriteClick().formOpened();

		LOG.info("Form was opened!");

		writeLetterPage.sbjInput(letter.getSubj()).whomInput(letter.getWhom()).formInput(letter.getText());

		LOG.info("The letter was created!");

		writeLetterPage.draftOpen().saveAndOpen().letterSaved();

		LOG.info("Your letter was saved!");

		LOG.info("Finish method 'writeSaveLetter'");
	}

	// Открыть папку с черновиками. Проверить тему и тело последнего письма.
	// Тема и тело письма совпадает с темой и телом, которое использовалось в
	// сценарии №2.
	public void checkDrafts(Letter letter) throws InterruptedException {
		LOG.info("Start method 'checkDrafts'");

		draftPage.openLastLetter(letter.getSubj()).checkSubj(letter.getSubj()).checkFormText(letter.getText())
				.btnSendClick().draftOpen().btnSendPackageClick();

		Thread.sleep(5000);

		sendPage.checkLetter(letter.getText()).draftOpen();

		LOG.info("Finish method 'checkDrafts'");
	}

	// Очистка черновиков
	public void clearDrafts() {
		LOG.info("Start method 'clearDrafts'");

		draftPage.checkAllDrafts().deleteMarked();

		LOG.info("All drafts were deleted!");

		LOG.info("Finish method 'clearDrafts'");
	}

	// Очистка отправленных писем
	public void clearSendLetters() throws InterruptedException {
		LOG.info("Start method 'clearSendLetters'");

		Thread.sleep(2000);

		draftPage.btnSendPackageClick();
		
		Thread.sleep(2000);
		
		sendPage.markLetters().deleteMarkedLetters();

		LOG.info("All letters were deleted!");

		LOG.info("Finish method 'clearSendLetters'");
	}

}
