package testCases;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testBase.TestBase;

public class LoginTest_Other extends TestBase {

	private static final long serialVersionUID = 1L;
	private static final String EMAIL = "aishwaryasoni534@gmail.com";
	private static final String PASSWORD = "password";
	private static final String HOST = "imap.gmail.com";
	private static final String PROTOCOL = "imaps";
	private static final String SEARCH_SUBJECT = "Viral Platform";

	@Test
	public static void loginOther() throws InterruptedException, IOException {

		initializaton();

		WebElement signUp = driver.findElement(By.linkText("Sign up"));
		signUp.click();
		WebElement other = driver.findElement(By.xpath("//p[text()=\"Other\"]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(other)).click();

		WebElement emailOther = driver.findElement(By.xpath("(//form[p[text()=\"Email\"]]//input)[3]"));
		WebElement fisrtName = driver.findElement(By.xpath("//div[p[text()=\"First Name\"]]//input"));
		WebElement lastName = driver.findElement(By.xpath("//div[p[text()=\"Last Name\"]]//input"));
		WebElement createAccount = driver.findElement(By.xpath("//button[contains(text(),\"Create Account\")]"));

		fisrtName.sendKeys("Aishwarya");
		lastName.sendKeys("Soni");
		emailOther.sendKeys(EMAIL);
		createAccount.click();
		driver.navigate().refresh();
		LoginTest_Other l = new LoginTest_Other();
		l.readEmail();
		// Close the browser
		driver.quit();

	}

	public void readEmail() throws IOException, InterruptedException {
		// Set up JavaMail properties
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", PROTOCOL);

		// Create a JavaMail session
		Session session = Session.getInstance(props);

		// Connect to the email server
		try {

			Store store = session.getStore(PROTOCOL);
			store.connect(HOST, EMAIL, PASSWORD);

			// Open the inbox folder

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);

			SubjectTerm subjectTerm = new SubjectTerm(SEARCH_SUBJECT);

			Message[] messages = inbox.search(subjectTerm);

			// Iterate through the found emails
			for (Message message : messages) {
				// Extract the verification link from the email body
				String verificationLink = extractVerificationLink(message);
				// String link = verificationLink;

				if (verificationLink != null) {

					performVerification(verificationLink);
				}

				// Mark the email as seen (optional)
				message.setFlag(Flags.Flag.SEEN, true);
			}

			// Close the folder and store
			inbox.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static String extractVerificationLink(Message message) throws MessagingException, IOException {
		String verificationLink1 = null;

		String s = message.getContent().toString();

		if (message.getContent() != null) {
			// System.out.println(s);

			Document doc = Jsoup.parse(s);
			Elements links = doc.select("a[href]");

			for (Element link : links) {
				verificationLink1 = link.attr("href");

			}

		}
		return verificationLink1;
	}

	private static void performVerification(String verificationLink) throws InterruptedException {

		driver.get(verificationLink);
		System.out.println("Link Launched");

		WebElement passwordField = driver.findElement(By.xpath("(//input[@type=\"password\"])[1]"));
		WebElement confPasswordField = driver.findElement(By.xpath("(//input[@type=\"password\"])[2]"));
		WebElement continueButton = driver.findElement(By.xpath("//button[text()=\"Continue\"]"));

		passwordField.sendKeys("Aish#1221");
		confPasswordField.sendKeys("Aish#1221");
		continueButton.click();

		System.out.println("Successfully Verified EmailID ");

	}
}

//