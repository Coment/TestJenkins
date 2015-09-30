package com.gmail.allpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.gmail.page.Page;

public class MoteveUserPage extends Page {

	public MoteveUserPage(WebDriver WebDriver) {
		super(WebDriver);
	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"email\"]")
	public WebElement email;
	@FindBy(how = How.ID, using = "password")
	public WebElement password;
	@FindBy(how = How.CLASS_NAME, using = "submit")
	public WebElement login;

	public void userLogin(String log, String pass) {
		email.clear();
		email.sendKeys(log);
		password.clear();
		password.sendKeys(pass);
		login.click();

	}

}
