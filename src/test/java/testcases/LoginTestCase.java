package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utility.Utility;

public class LoginTestCase {

	Utility utility;
	HomePage home;
	LoginPage loginPage;
	WebDriver driver;
	List<String> usernamePasswordList;

	@BeforeMethod
	public void initilization() throws IOException {
		utility = new Utility();
		this.driver = utility.getWebDriver();
		home = new HomePage(driver);
		driver.get(Utility.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginPage = new LoginPage(driver);
		usernamePasswordList = Utility.readExcel();
	}

	@Test
	public void verifyValidLogin() throws IOException {
		home.doLogin(usernamePasswordList.get(0), usernamePasswordList.get(1));
		assertEquals(loginPage.getLoginSuccessfulMsg().getText(), "Login Successfully");
	}

	@Test(dataProvider = "dataProvider")
	public void verifyLogOut(String userName, String password) {
		home.doLogin(userName, password);
		loginPage.getSignOffLink().click();
		assertTrue(home.getUserNameTextBox().isDisplayed());
	}

	@DataProvider(name = "dataProvider")
	public String[][] dataProvider() throws IOException {
		usernamePasswordList = Utility.readExcel();
		String array[][] = new String[1][2];
		array[0][0] = usernamePasswordList.get(0);
		array[0][1] = usernamePasswordList.get(1);
		return array;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			Utility.takeScreenShot(driver, result.getName());
		}
		driver.quit();
	}
}
