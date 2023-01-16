package testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.Utility;



public class FindBrokenLinksTestCase {

	WebDriver driver;
	Utility utility;

	@BeforeMethod
	public void initilize() throws IOException {
		utility = new Utility();
		driver = utility.getWebDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void brokenLinks() throws MalformedURLException, IOException {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Number of links are " + links.size());
		for (WebElement link : links) {
			String linktext = link.getAttribute("href");

			// HttpURLConnection huc=null;
			URLConnection url = new URL(linktext).openConnection();
			HttpURLConnection huc = (HttpURLConnection) url;
			huc.connect();

			int responseCode = huc.getResponseCode();
			assertEquals(huc.getResponseCode(), 200);
			if (responseCode == 200) {
				System.out.println(linktext + " : " + responseCode + " : " + "working fine");
			} else {
				System.out.println(linktext + " : " + responseCode + " : " + "working not fine");
			}

		}
	}
}
