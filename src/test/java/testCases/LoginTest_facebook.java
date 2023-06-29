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

public class LoginTest_facebook extends TestBase{
	@Test
	public static void loginFacebook() throws InterruptedException {
		
		initializaton();

		WebElement signInWithFacebookButton = driver.findElement(By.xpath("//button[contains(text(),\"Continue with Facebook\")]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(signInWithFacebookButton)).click();
		// Perform the login process with Google
		// Replace the following lines with your actual test case steps

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> it = allWindowHandles.iterator();

		while (it.hasNext()) {
			String ChildWindow = it.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.manage().window().maximize();

				WebElement emailInput = driver.findElement(By.id("email"));
				WebElement passwordInput = driver.findElement(By.id("pass"));
				WebElement loginButton = driver.findElement(By.name("login"));

				emailInput.sendKeys("aishwarya@gmail.com");
				passwordInput.sendKeys("password");
				loginButton.click();

				
			}
		}

		driver.quit();
	}
}
