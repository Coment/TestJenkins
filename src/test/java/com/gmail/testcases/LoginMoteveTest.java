package com.gmail.testcases;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Attachment;

import com.gmail.testbase.MyTestListener;
import com.gmail.testbase.TestBase;
import com.gmail.webdriver.WebDriverFactory;

public class LoginMoteveTest {
	public class LoginPageTestSuite extends TestBase {
		
		@Test(groups = { "group" })
		public void loginMoteve() throws InterruptedException {
			eventDriver.get(testUrl2);
			moteve.login(getUsernameMoteve(), getPasswordMoteve());
			Assert.assertTrue(moteve.isLogin());
		}

	}
}