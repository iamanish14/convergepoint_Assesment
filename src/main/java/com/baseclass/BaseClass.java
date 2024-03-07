package com.baseclass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.Timeout;

public class BaseClass {
	public static WebDriver driver;

	public static final Logger LOGGER = LogManager.getLogger(BaseClass.class);

	public static void launchbrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	}

	public static void launchurl(String value) {
		driver.get(value);

	}

	public static void selectelemnet(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element)).click();

	}

	public void screenshot(String fileName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshots\\" + fileName + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void javascript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	public static void scrollview(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public static void timeout() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public String text(WebDriver driver, WebElement element) {
		return element.getText();
	}

	public static void windowhandles() {
		String parentwindow = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> iterator = s.iterator();

		while (iterator.hasNext()) {
			String childwindow = iterator.next();
			if (!parentwindow.equals(childwindow)) {
				driver.switchTo().window(childwindow);

			}
		}
	}
}
