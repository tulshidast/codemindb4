package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage  {

	WebDriver driver;
	WebElement registrationLink;
	WebElement homeLink;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getRegistrationLink() {
		this.setRegistrationLink();
		return registrationLink;
	}

	public void setRegistrationLink() {
		this.registrationLink = driver.findElement(By.xpath("//a[text()='REGISTER']"));
	}

	public WebElement getHomeLink() {
		this.setHomeLink();
		return homeLink;
	}

	public void setHomeLink() {
		this.homeLink = driver.findElement(By.xpath("//a[text()='Home']"));
	}

}
