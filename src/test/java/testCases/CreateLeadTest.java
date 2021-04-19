package testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import commonFunctions.ExcelReader;
import pages.LeadPage;

public class CreateLeadTest extends BaseTest{

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "getdata")
	public void createLeadTest(Hashtable<String,String> data) throws InterruptedException {
		LeadPage lp = new LeadPage();
		lp.createLead(data);
		lp.convertLead();
	}
}
