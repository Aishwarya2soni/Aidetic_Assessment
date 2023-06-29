package testCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.TestBase;

public class LoginTest_google extends TestBase{
	@Test
	public static void loginGoogle() throws InterruptedException {
		
		initializaton();
//		
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@title=\"Sign in with Google Button\"]"));

		driver.switchTo().frame(frame3);

		// Find the "Continue with Google" button
		WebElement continueWithGoogleButton = driver
				.findElement(By.xpath("(//div[@class=\"haAclf\"]//span[contains(text(),'Continue with Google')])[1]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(continueWithGoogleButton)).click();

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> it = allWindowHandles.iterator();

		while (it.hasNext()) {
			String ChildWindow = it.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.manage().window().maximize();

				WebElement emailField = driver.findElement(By.id("identifierId"));
				emailField.sendKeys("aishwarya@gmail.com");

				WebElement next = driver.findElement(By.xpath("//button[span[text()=\"Next\"]]"));
				next.click();

				// Enter Google password
				WebElement passwordField = driver.findElement(By.name("Passwd"));
				passwordField.sendKeys("password");
				next.click();


			}
		}

		driver.quit();
	}
}
