package testCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.TestBase;

public class LoginTest_apple extends TestBase {
	@Test
	public static void loginApple() throws InterruptedException {
		
		initializaton();

		// List<WebElement> frame1 = driver.findElements(By.xpath("//iframe"));
		// System.out.println(frame1);
		// System.out.println(frame1.size());
//		
//		WebElement frame3 = driver.findElement(By.xpath("//iframe[@title=\"Sign in with Google Button\"]"));
//		driver.switchTo().frame(frame3);
//		System.out.println("Aish---------------------------------");
//		System.out.println(driver.getTitle());

		// Find the "Continue with Google" button and click it
		WebElement signInWithAppleButton = driver
				.findElement(By.xpath("//button[@buttontext=\"Continue with Apple\"]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(signInWithAppleButton)).click();
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

				// couldnt login as after clicking on button , it redirect to below link

				// "https://appleid.apple.com/auth/authorize?client_id=com.vnplatform.dev&redirect_uri=https%3A%2F%2Fauth.dev.vntech.io&response_type=code%20id_token&scope=name%20email&response_mode=web_message&frame_id=e86f730c-2039-470e-b3f1-1506b8f0a170&m=11&v=1.5.5"

			}
		}

		driver.quit();
	}
}