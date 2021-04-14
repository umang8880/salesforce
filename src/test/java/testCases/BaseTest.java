package testCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import commonFunctions.BasePage;

public class BaseTest {

	@BeforeSuite
	public void initialization() {
		BasePage.setUp();
	}
	@AfterSuite
	public void teardown() throws InterruptedException {
		BasePage.quit();
	}
}
