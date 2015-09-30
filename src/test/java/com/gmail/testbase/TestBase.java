package com.gmail.testbase;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.gmail.allpages.AccountPage;
import com.gmail.allpages.HomePage;
import com.gmail.allpages.MotevePage;
import com.gmail.allpages.MoteveUserPage;
import com.gmail.util.Browser;
import com.gmail.util.PropertyLoader;
import com.gmail.webdriver.WebDriverFactory;

/**
 * Base class for all the test classes
 * 
 * @author Yuri Demchenko
 */

public class TestBase {

	// protected WebDriver webDriver;
	protected EventFiringWebDriver eventDriver;
	protected String websiteUrl;
	protected Browser browser;
	
	public WebDriver webDriver2; 

	protected static String testUrl;
	protected static String testUrl2;
	protected static String username;
	protected static String password;
	protected static String usernameMoteve;
	protected static String passwordMoteve;
	protected static String email;
	protected static String subject;
	protected static String content;

	public static String getEmail() {
		return email;
	}

	public static String getSubject() {
		return subject;
	}

	public static String getContent() {
		return content;
	}

	public static String getUsermail() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static String getUrl() {
		return testUrl;
	}

	protected HomePage home;

	protected AccountPage user;
	protected MotevePage moteve;
	protected MoteveUserPage login;
	
	@Parameters({ "browserName" })
	@BeforeMethod(groups = { "group" })
	public void init(String browserName) throws Exception {

		browser = new Browser();
		browser.setName(browserName);
		URL hubUrl = new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setBrowserName("firefox");
		capabilities.setJavascriptEnabled(true);
		capabilities.setPlatform(Platform.LINUX);
		webDriver2 = new RemoteWebDriver(hubUrl, capabilities);
		testUrl = PropertyLoader.loadProperty("test.url");
		testUrl2 = PropertyLoader.loadProperty("test.url2");
		username = PropertyLoader.loadProperty("user.username");
		password = PropertyLoader.loadProperty("user.password");
		usernameMoteve = PropertyLoader.loadProperty("userMoteve.name");
		passwordMoteve = PropertyLoader.loadProperty("userMoteve.password");
		subject = PropertyLoader.loadProperty("mail.subject");
		content = PropertyLoader.loadProperty("mail.content");

		//WebDriver webDriver2 = WebDriverFactory.getInstance(browser.getName());
		
		webDriver2.manage().window().maximize();
		
		eventDriver = new EventFiringWebDriver(webDriver2);
		eventDriver.register(new WebDriverListener());
		webDriver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//eventDriver.get(testUrl2);

		home = PageFactory.initElements(webDriver2, HomePage.class);
		user = PageFactory.initElements(webDriver2, AccountPage.class);
		moteve = PageFactory.initElements(eventDriver, MotevePage.class);
		login = PageFactory.initElements(eventDriver, MoteveUserPage.class);
	}

	public static String getUsernameMoteve() {
		return usernameMoteve;
	}

	public static void setUsernameMoteve(String usernameMoteve) {
		TestBase.usernameMoteve = usernameMoteve;
	}

	public static String getPasswordMoteve() {
		return passwordMoteve;
	}

	public static void setPasswordMoteve(String passwordMoteve) {
		TestBase.passwordMoteve = passwordMoteve;
	}

	@AfterMethod(groups = { "group" })
	public void reopenApp() throws Exception {
		 
		if (webDriver2 != null) {
			webDriver2.quit();
			webDriver2 = null;
		}
		
	}

}
