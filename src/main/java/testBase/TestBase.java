package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {

	
	protected static WebDriver driver;
	public static Properties properties;
	protected static ChromeOptions chromeOptions;
	

	
	protected static void initializaton() throws InterruptedException {
		
		try {
			properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("C:/Users/hp/eclipse-workspace-HP/Viralnation_Secure/src/main/java/config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Downloads\\chromedriver_win32\\chromedriver.exe");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");

			
		}else
		{
			System.out.println("Aish-------------");
		}
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(properties.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()=\"Accept All\"]")).click();
		Thread.sleep(3000);

	}
	
}
