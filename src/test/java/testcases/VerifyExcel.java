package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import utility.Utility;

public class VerifyExcel {

	@Test
	public void readExcelFile() throws IOException {
		Utility.readExcel();
	}
}
