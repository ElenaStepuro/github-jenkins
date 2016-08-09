package com.epam.elena_stepuro.framework.reporting.run_tests.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import com.epam.elena_stepuro.framework.reporting.framework.ui.driver.Driver;
import com.epam.elena_stepuro.framework.reporting.run_tests.utils.ScreenShot;
import com.epam.elena_stepuro.framework.reporting.run_tests.utils.ScreenshotListener;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.business_ob.Account;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.business_ob.Letter;
import com.epam.elena_stepuro.framework.reporting.run_tests.yandex.services.YandexService;
import org.uncommons.reportng.HTMLReporter;

@Listeners({ HTMLReporter.class, ScreenshotListener.class })
public class YandexTest {

	private static final Logger LOG = Logger.getLogger(YandexTest.class);
	private Account account;
	private YandexService service = new YandexService();

	@BeforeClass
	public void openBrows() {
		LOG.info("Start test 'openBrows'");

		System.setProperty("org.uncommons.reportng.escape-output", "false");

		ScreenShot.deleteAll();

		LOG.info("All screenshots were deleted from folder");

		Driver.getWebDriverInstance().get("http://www.yandex.ru");

		LOG.info("Finish test 'openBrows'");
	}

	@Test
	public void testLogonYandex() {
		LOG.info("Start test 'testLogonYandex'");
		
		account = new Account();

		account.setLogin("stepuro.e@yandex.ru");
		account.setPasswd("stepuro_elena");

		service.logonYandex(account);

		LOG.info("Finish test 'testLogonYandex'");
		throw new RuntimeException();
	}

	@Test(dependsOnMethods = "testLogonYandex", dataProvider = "testData")
	public void testWriteSaveLetter(Letter letter) throws InterruptedException {
		LOG.info("Start test 'testWriteSaveLetter'");

		service.writeSaveLetter(letter);

		LOG.info("Finish test 'testWriteSaveLetter'");
	}

	@Test(dependsOnMethods = "testWriteSaveLetter", dataProvider = "testData")
	public void testCheckDrafts(Letter letter) throws InterruptedException {
		LOG.info("Start test 'testCheckDrafts'");

		service.checkDrafts(letter);

		LOG.info("Finish test 'testCheckDrafts'");
	}

	@Test(dependsOnMethods = "testCheckDrafts")
	public void testClearDrafts() {
		LOG.info("Start test 'testClearDrafts'");

		service.clearDrafts();

		LOG.info("Finish test 'testClearDrafts'");
	}

	@Test(dependsOnMethods = "testClearDrafts")
	public void testClearSendLetters() throws InterruptedException {
		LOG.info("Start test 'testClearSendLetters'");

		service.clearSendLetters();

		LOG.info("Finish test 'testClearSendLetters'");
	}

	// Данные ко тестам
	@DataProvider
	private Object[][] testData() throws FileNotFoundException {
		List<String> strings = new ArrayList<String>();
		BufferedReader dataFile = new BufferedReader(new FileReader("DataForYandexTest.txt"));
		String line;
		try {
			while ((line = dataFile.readLine()) != null) {
				strings.add(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		Letter letter = new Letter();
		Letter letter1 = new Letter();

		letter.setWhom(strings.get(0));
		letter.setSubj(strings.get(1));
		letter.setText(strings.get(2));

		letter1.setWhom(strings.get(3));
		letter1.setSubj(strings.get(4));
		letter1.setText(strings.get(5));

		return new Object[][] { { letter }, { letter1 } };
	}

}
