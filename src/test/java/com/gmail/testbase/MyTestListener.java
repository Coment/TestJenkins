package com.gmail.testbase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;

import ru.yandex.qatools.allure.annotations.Attachment;

import com.gmail.webdriver.WebDriverFactory;

/**
 * Class contains a method's for report's
 */

public class MyTestListener extends TestListenerAdapter {

	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	private void printTestResults(ITestResult result) {
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log(
					"Test Method had the following parameters : " + params,
					true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
		takeScreenshot(result);
		makeScreenshot("123");
	}

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
		makeScreenshot("skipp");
	}

	public void onTestSuccess(ITestResult arg0) {
		makeScreenshot("success");
		printTestResults(arg0);

	}

	public void onTestFailure(ITestResult arg0) {
		makeScreenshot("fail");
		printTestResults(arg0);
	}
	
	@Attachment(value = "{0}", type = "image/png")
	public static byte[] makeScreenshot(String name) {
		return ((TakesScreenshot) WebDriverFactory.webDriver)
				.getScreenshotAs(OutputType.BYTES);
	}

	public void takeScreenshot(ITestResult result) {
		String folder = SCREENSHOT_FOLDER + result.getName();
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			SimpleDateFormat formater = new SimpleDateFormat(
					"dd_MM_yyyy_hh_mm_ss");
			// TODO get screenshot
			Thread.sleep(3000);
			File f = ((TakesScreenshot) WebDriverFactory.webDriver)
					.getScreenshotAs(OutputType.FILE);
			String fileName = result.getName() + "_"
					+ formater.format(Calendar.getInstance().getTime())
					+ SCREENSHOT_FORMAT;
			FileUtils.copyFile(f, new File(dir.getAbsoluteFile() + "/"
					+ fileName));
			File directory = new File(".");
			String newFileNamePath = directory.getCanonicalPath();
			Reporter.log("<a href=" + newFileNamePath + "/" + folder + "/"
					+ fileName + " target='_blank' >"
					+ "<p><br/><img src=\"file:///" + newFileNamePath + "/"
					+ folder + "/" + fileName
					+ "\" width=\"600\" height=\"338\" alt=\"\"/>"
					+ "<br/></p></a>", true);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTestDirectory(ITestResult result) {
		// get the class name for grouping tests in class folders
		File dir = new File(SCREENSHOT_FOLDER + result.getName());
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

}
