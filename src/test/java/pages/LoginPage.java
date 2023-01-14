package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	WebDriver driver;
	WebElement loginSuccessfulMsg;
	WebElement sinOffLink;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebElement getLoginSuccessfulMsg() {
		this.setLoginSuccessfulMsg();
		return loginSuccessfulMsg;
	}

	public void setLoginSuccessfulMsg() {
		this.loginSuccessfulMsg = driver.findElement(By.xpath("//h3[text()='Login Successfully']"));
	}

	public WebElement getSignOffLink() {
		this.setSignOffLink();
		return sinOffLink;
	}

	public void setSignOffLink() {
		this.sinOffLink = driver.findElement(By.xpath("//a[text()='SIGN-OFF']"));
	}

}
