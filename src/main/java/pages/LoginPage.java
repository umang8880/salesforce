package pages;

import static org.testng.Assert.assertEquals;

import java.util.Hashtable;

import commonFunctions.BasePage;

public class LoginPage extends BasePage{
	public void dologin(Hashtable<String,String> data) throws InterruptedException{			
		typeatext("username_ID", data.get("username"));
		typeatext("password_ID", data.get("password"));
		click("login_ID");
		Thread.sleep(3000);
		assertEquals(driver.getTitle(), data.get("homepageTitle"));
	}
}
