package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utility {

	public WebDriver getWebDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", getProperty("chromedriverpath"));
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static String getProperty(String key) throws IOException {
		File file = new File("src/test/resources/testData.properties");
		FileInputStream fileInputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		return properties.getProperty(key);
	}

	public static List<String> readExcel() throws IOException {
		File file = new File("src/test/resources/testDataExcel.xls");
		FileInputStream fileInputStream = new FileInputStream(file);
		// if we have xlsx replace HSSF with XSSF
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		List<String> userNamePassword = new ArrayList<String>();

		int lastRowNumber = hssfSheet.getLastRowNum();

		for (int i = 1; i <= lastRowNumber; i++) {
			HSSFRow hssfRow = hssfSheet.getRow(i);
			int lastCellNum = hssfRow.getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) {

				userNamePassword.add(hssfRow.getCell(j).getStringCellValue());

			}
		}
		return userNamePassword;
	}

	public static void takeScreenShot(WebDriver driver, String name) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("screenshot/" + name + ".png"));
	}
}
