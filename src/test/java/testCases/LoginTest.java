package testCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import commonFunctions.ExcelReader;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "getdata")
	public void loginTest(Hashtable<String,String> data) throws InterruptedException, IOException {
		LoginPage lp = new LoginPage();
		lp.dologin(data);
	}
}
