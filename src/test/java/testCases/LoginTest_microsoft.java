package testCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.TestBase;

public class LoginTest_microsoft extends TestBase {
	@Test
	public static void loginMicrosoft() throws InterruptedException {

		initializaton();

		WebElement signInWithAppleButton = driver.findElement(By.xpath("//p[text()=\"Continue with Microsoft\"]"));
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

				WebElement emailID = driver.findElement(By.name("loginfmt"));
				emailID.sendKeys("aishwarya@gmail.com");
				WebElement next = driver.findElement(By.id("idSIButton9"));
				next.click();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				try {

					Thread.sleep(3000);
					js.executeScript("document.getElementById('i0118').focus();");
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("passwd")));
					WebElement passM = driver.findElement(By.name("passwd"));
					Thread.sleep(3000);
					js.executeScript("arguments[0].value='password';", passM);

					js.executeScript("arguments[0].focus();", passM);

				} catch (StaleElementReferenceException e) {
					e.printStackTrace();
					System.out.println(e);
				}

				WebElement signIn = driver.findElement(By.id("idSIButton9"));
				signIn.click();

			
				js.executeScript("document.getElementById('idBtn_Back').focus();");
				// Thread.sleep(3000);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idBtn_Back")));
				WebElement staySignedIn = driver.findElement(By.id("idBtn_Back"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", staySignedIn);


				
			}

		}
		driver.quit();
	}
}
//}