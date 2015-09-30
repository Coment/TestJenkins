package com.gmail.allpages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import ru.yandex.qatools.allure.annotations.Attachment;

import com.gmail.page.Page;
import com.gmail.webdriver.WebDriverFactory;

public class MotevePage extends Page {
	public MotevePage(WebDriver webDriver) {
		super(webDriver);

	}

	@FindBy(how = How.XPATH, using = "/html/body/menu/div/div[3]/div[1]")
	public WebElement login;
	@FindBy(how = How.XPATH, using = "/html/body/menu/modal/div/div/div[1]/login/div/form/div[1]/div[1]/input[2]")
	public WebElement email;
	@FindBy(how = How.XPATH, using = "/html/body/menu/modal/div/div/div[1]/login/div/form/div[1]/div[2]/input")
	public WebElement password;
	@FindBy(how = How.XPATH, using = "/html/body/menu/modal/div/div/div[1]/login/div/form/div[2]/button")
	public WebElement submit;
	@FindBy(how = How.XPATH, using="/html/body/div[3]/ng-view/modal/div/div/div[2]")
	public WebElement quit; 
	@FindBy(how = How.XPATH, using="/html/body/div[3]/ng-view/art-list/div/div[6]/div[1]")
	public WebElement picture;
	@FindBy(how = How.XPATH, using="/html/body/div[2]/ng-view/div/div/art-head/div[2]/div[2]/div[3]/div[3]/div[1]")
	public WebElement like;
	@FindBy(how = How.XPATH, using="/html/body/div[2]/ng-view/div/div/art-head/div[2]/div[2]/div[3]/div[2]/button")
	public WebElement buy;
	@FindBy(how = How.XPATH, using="/html/body/div[2]/ng-view/form/div[5]/div[2]/div[1]/div[1]/div/input")
	public WebElement nameBuy;
	@FindBy(how = How.XPATH, using="/html/body/div[2]/ng-view/form/div[9]/button[2]")
	public WebElement cancel;
	@FindBy(how = How.XPATH, using="/html/body/menu/div/div[4]/div[4]/div[2]")
	public WebElement logout;
	@FindBy(how = How.XPATH, using="/html/body/menu/div/div[4]/div[2]/div")
	public WebElement menu;
	
	

	public void login(String log, String pass) throws InterruptedException {
		quit.click();
		login.click();
		email.sendKeys(log);
		Thread.sleep(1000);
		password.sendKeys(pass);
		submit.click();
		Thread.sleep(1000);
		/*picture.click();
		Thread.sleep(3000);
		like.click();
		Thread.sleep(1000);
		buy.click();
		Thread.sleep(1000);
		nameBuy.sendKeys("Жора");
		Thread.sleep(1000);
		nameBuy.clear();
		Thread.sleep(1000);
		cancel.click();
		Thread.sleep(1000);*/
		menu.click();
		logout.click();
	}
	public boolean isLogin() {
		Reporter.log("Checking the avatar image presence on screen", true);
		return login.isDisplayed();
	}
}